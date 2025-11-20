package com.example.EMS_v2.controllers;

import com.example.EMS_v2.dtos.PositionDto;
import com.example.EMS_v2.entities.Department;
import com.example.EMS_v2.entities.Position;
import com.example.EMS_v2.mappers.PositionMapper;
import com.example.EMS_v2.repositories.DepartmentRepo;
import com.example.EMS_v2.repositories.PositionRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("*/api/positions")
@CrossOrigin(origins = "*")
public class PositionController {
    private final PositionRepo positionRepository;
    private final PositionMapper positionMapper;
    private final DepartmentRepo departmentRepository;

    public PositionController(PositionRepo positionRepository, PositionMapper positionMapper, DepartmentRepo departmentRepository) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
        this.departmentRepository = departmentRepository;
    }

    // ---- GET all position ----
    @GetMapping
    public List<PositionDto> getAllPosition() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toDto)
                .toList();
    }

    // ---- GET 1 Position ----
    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getPosition(@PathVariable Long id){
        var position = positionRepository.findById(id).orElse(null);
        if (position == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(positionMapper.toDto(position));
    }

    // ---- CREATE POSITION ----
    @PostMapping
    public ResponseEntity<PositionDto> createPosition(@RequestBody PositionDto positionDto){

        Position position = positionMapper.toEntity(positionDto);

        if (positionDto.getDepartmentId() != null){
            Optional<Department> departmentOpt = departmentRepository.findById(positionDto.getDepartmentId());
            if (departmentOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            position.setDepartmentName(departmentOpt.get().getName());
        }else {
            return ResponseEntity.badRequest().build();
        }

        Position savedPosition = positionRepository.save(position);
        return ResponseEntity.ok(positionMapper.toDto(savedPosition));
    }

    // ---- UPDATE POSITION ----
    @PutMapping("/{id}")
    public ResponseEntity<PositionDto> updatePosition(
            @PathVariable Long id,
            @RequestBody PositionDto updatedDto) {

        Optional<Position> existingOpt = positionRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Position existing = existingOpt.get();
        positionMapper.updatePositionFromDto(updatedDto, existing);

        if (updatedDto.getDepartmentId() != null) {
            Optional<Department> departmentOpt = departmentRepository.findById(updatedDto.getDepartmentId());
            if (departmentOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            existing.setDepartmentName(departmentOpt.get().getName());
        }

        Position saved = positionRepository.save(existing);
        return ResponseEntity.ok(positionMapper.toDto(saved));
    }

    // ---- Delete Position ----
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        if (!positionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        positionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

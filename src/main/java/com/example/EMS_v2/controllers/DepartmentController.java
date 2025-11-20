package com.example.EMS_v2.controllers;


import com.example.EMS_v2.dtos.DepartmentDto;
import com.example.EMS_v2.entities.Department;
import com.example.EMS_v2.mappers.DepartmentMapper;
import com.example.EMS_v2.repositories.DepartmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepo departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentController(DepartmentRepo departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    // ---- GET ALL DEPARTMENT ----
    @GetMapping
    public List<DepartmentDto> getAllDepartment(){
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    // ---- GET 1 Department ----
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id){
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departmentMapper.toDto(department));
    }

    // ---- CREATE DEPARTMENT ----
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        Department department = departmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(departmentMapper.toDto(savedDepartment));
    }

    // ---- UPDATE DEPARTMENT ----
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentDto updateDto) {
        return departmentRepository.findById(id)
                .map(existing -> {
                    departmentMapper.updateDepartmentFromDto(updateDto, existing);
                    Department savedDepartment = departmentRepository.save(existing);
                    return ResponseEntity.ok(departmentMapper.toDto(savedDepartment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ---- DELETE Department ----
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (!departmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        departmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

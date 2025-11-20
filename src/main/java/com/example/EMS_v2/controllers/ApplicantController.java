package com.example.EMS_v2.controllers;

import com.example.EMS_v2.dtos.ApplicantDto;
import com.example.EMS_v2.entities.Applicant;
import com.example.EMS_v2.entities.Position;
import com.example.EMS_v2.mappers.ApplicantMapper;
import com.example.EMS_v2.repositories.ApplicantRepo;
import com.example.EMS_v2.repositories.PositionRepo;
import com.example.EMS_v2.services.ApplicantService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applicants")
@CrossOrigin(origins = "*")
public class ApplicantController {
    private final ApplicantRepo applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final ApplicantService applicantService;
    private final PositionRepo positionRepository;

    public ApplicantController(ApplicantRepo applicantRepository, ApplicantMapper applicantMapper, ApplicantService applicantService, PositionRepo positionRepository) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
        this.applicantService = applicantService;
        this.positionRepository = positionRepository;
    }

    // ---- GET all Applicant ----
    @GetMapping
    public ResponseEntity<Page<ApplicantDto>> getAllApplicant(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<ApplicantDto> applicantPage = applicantService.getAllApplicants(page, size);
        return ResponseEntity.ok(applicantPage);
    }

//    @GetMapping
//    public List<ApplicantDto> getAllApplicants() {
//        return applicantRepository.findAll()
//                .stream()
//                .map(applicantMapper::toDto)
//                .toList();
//    }

    // ---- GET 1 Applicant ----
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDto> getApplicant(@PathVariable Long id){
        var applicant = applicantRepository.findById(id).orElse(null);
        if (applicant == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(applicantMapper.toDto(applicant));
    }

    // ---- CREATE APPLICANT ----
    @PostMapping
    public ResponseEntity<ApplicantDto> createApplicant(@RequestBody ApplicantDto dto) {

        // Convert DTO → Entity
        Applicant applicant = applicantMapper.toEntity(dto);

        // ✅ Validate and link Position (foreign key)
        if (dto.getPositionId() != null) {
            Optional<Position> positionOpt = positionRepository.findById(dto.getPositionId());
            if (positionOpt.isEmpty()) {
                return ResponseEntity.badRequest().build(); // 400 if invalid position ID
            }
            applicant.setPositionTitle(positionOpt.get().getTitle());
        } else {
            return ResponseEntity.badRequest().build(); // Missing required field
        }

        // ✅ Save to database
        Applicant saved = applicantRepository.save(applicant);

        // ✅ Return the saved DTO
        return ResponseEntity.ok(applicantMapper.toDto(saved));
    }

    // ---- UPDATE APPLICANT ----
    @PutMapping("/{id}")
    public ResponseEntity<ApplicantDto> updateApplicant(
            @PathVariable Long id,
            @RequestBody ApplicantDto updatedDto) {

        Optional<Applicant> existingOpt = applicantRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Applicant existing = existingOpt.get();
        String oldStatus = existing.getStatus();

        applicantMapper.updateApplicantFromDto(updatedDto, existing);
        if (updatedDto.getPositionId() != null) {
            Optional<Position> positionOpt = positionRepository.findById(updatedDto.getPositionId());
            if (positionOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Position position = positionOpt.get();
            existing.setPositionId(position.getId());
            existing.setPositionTitle(position.getTitle());
        }

        Applicant saved = applicantRepository.save(existing);

        // ✅ Check if status changed to "Accepted"
        if (!"Accepted".equalsIgnoreCase(oldStatus)
                && "Accepted".equalsIgnoreCase(saved.getStatus())) {
            applicantService.convertApplicantToEmployee(saved);
        }

        return ResponseEntity.ok(applicantMapper.toDto(saved));
    }

    // ---- DELETE APPLICANT ----
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        if (!applicantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        applicantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

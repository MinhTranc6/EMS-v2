package com.example.EMS_v2.controllers;

import com.example.EMS_v2.dtos.SalaryDto;
import com.example.EMS_v2.entities.Salary;
import com.example.EMS_v2.mappers.SalaryMapper;
import com.example.EMS_v2.repositories.EmployeeRepo;
import com.example.EMS_v2.repositories.SalaryRepo;
import com.example.EMS_v2.services.SalaryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payrolls")
@CrossOrigin(origins = "*")
public class SalaryController {
    private final SalaryRepo salaryRepository;
    private final EmployeeRepo employeeRepository;
    private final SalaryMapper salaryMapper;
    private final SalaryService salaryService;

    public SalaryController(SalaryRepo salaryRepository, EmployeeRepo employeeRepository, SalaryMapper salaryMapper, SalaryService salaryService) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
        this.salaryMapper = salaryMapper;
        this.salaryService = salaryService;
    }

    @GetMapping
    public ResponseEntity<Page<SalaryDto>> getAllSalary(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<SalaryDto> salaryPage = salaryService.getAllSalaries(page, size);
        return ResponseEntity.ok(salaryPage);
    }

    // GET all salaries for one employee
//    @GetMapping("/employee/{employeeId}")
//    public List<SalaryDto> getSalariesByEmployee(@PathVariable Long employeeId) {
//        return salaryRepository.findByEmployeeId(employeeId)
//                .stream()
//                .map(salaryMapper::toDto)
//                .toList();
//    }

    // GET a specific salary record
    @GetMapping("/{id}")
    public ResponseEntity<SalaryDto> getSalary(@PathVariable Long id) {
        return salaryRepository.findById(id)
                .map(salaryMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE a salary record
    @PutMapping("/{id}")
    public ResponseEntity<SalaryDto> updateSalary(
            @PathVariable Long id,
            @RequestBody SalaryDto updatedDto) {

        Optional<Salary> optional = salaryRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Salary existing = optional.get();

        // ✅ Validate employee ID
        if (updatedDto.getEmployeeId() != null &&
                !employeeRepository.existsById(updatedDto.getEmployeeId())) {
            return ResponseEntity.badRequest().build();
        }

        // ✅ Update scalar fields from DTO
        salaryMapper.updateSalaryFromDto(updatedDto, existing);

        // ✅ Populate flattened employee info
        if (updatedDto.getEmployeeId() != null) {
            employeeRepository.findById(updatedDto.getEmployeeId())
                    .ifPresent(employee -> {
                        existing.setEmployeeId(employee.getId());
                    });
        }

        Salary saved = salaryRepository.save(existing);
        return ResponseEntity.ok(salaryMapper.toDto(saved));
    }



    // DELETE a salary record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        if (!salaryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        salaryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

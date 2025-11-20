package com.example.EMS_v2.controllers;

import com.example.EMS_v2.dtos.EmployeeDto;
import com.example.EMS_v2.entities.Employee;
import com.example.EMS_v2.mappers.EmployeeMapper;
import com.example.EMS_v2.repositories.EmployeeRepo;
import com.example.EMS_v2.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepo employeeRepository;
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeMapper employeeMapper, EmployeeRepo repository, EmployeeService employeeService){
        this.employeeMapper = employeeMapper;
        this.employeeRepository = repository;
        this.employeeService = employeeService;
    }

    // ---- READ ALL ----
    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployee(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<EmployeeDto> employeePage = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(employeePage);
    }

    // ---- READ ONE ----
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id){
        var employee = employeeRepository.findById(id).orElse(null);
        if (employee == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employeeMapper.toDto(employee));
    }

    // ---- UPDATE ----
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto updatedDto) {
        return employeeRepository.findById(id)
                .map(existing -> {
                    employeeMapper.updateEmployeeFromDto(updatedDto, existing);
                    Employee saved = employeeRepository.save(existing);
                    return ResponseEntity.ok(employeeMapper.toDto(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ---- DELETE ----
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.EMS_v2.services;

import com.example.EMS_v2.dtos.EmployeeDto;
import com.example.EMS_v2.entities.Employee;
import com.example.EMS_v2.mappers.EmployeeMapper;
import com.example.EMS_v2.repositories.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepo employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    // ✅ NEW PAGINATED METHOD
    public Page<EmployeeDto> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(employeeMapper::toDto);
    }
}

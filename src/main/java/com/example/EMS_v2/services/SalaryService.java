package com.example.EMS_v2.services;


import com.example.EMS_v2.dtos.SalaryDto;
import com.example.EMS_v2.entities.Salary;
import com.example.EMS_v2.mappers.SalaryMapper;
import com.example.EMS_v2.repositories.SalaryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    private final SalaryRepo salaryRepository;
    private final SalaryMapper salaryMapper;

    public SalaryService(SalaryRepo salaryRepository, SalaryMapper salaryMapper) {
        this.salaryRepository = salaryRepository;
        this.salaryMapper = salaryMapper;
    }

    // ✅ NEW PAGINATED METHOD
    public Page<SalaryDto> getAllSalaries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Salary> salaries = salaryRepository.findAll(pageable);
        return salaries.map(salaryMapper::toDto);
    }
}

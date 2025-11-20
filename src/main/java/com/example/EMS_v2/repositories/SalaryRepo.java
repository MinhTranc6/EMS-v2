package com.example.EMS_v2.repositories;

import com.example.EMS_v2.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepo extends JpaRepository<Salary, Long> {
}

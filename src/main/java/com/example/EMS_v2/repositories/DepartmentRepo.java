package com.example.EMS_v2.repositories;

import com.example.EMS_v2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}

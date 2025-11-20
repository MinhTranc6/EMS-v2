package com.example.EMS_v2.repositories;

import com.example.EMS_v2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    boolean existsByContact(String contact);
}

package com.example.EMS_v2.repositories;

import com.example.EMS_v2.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {
}

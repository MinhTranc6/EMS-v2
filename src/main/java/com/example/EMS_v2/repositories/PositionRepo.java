package com.example.EMS_v2.repositories;

import com.example.EMS_v2.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepo extends JpaRepository<Position, Long> {
}

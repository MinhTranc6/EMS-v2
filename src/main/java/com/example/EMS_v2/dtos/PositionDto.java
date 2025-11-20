package com.example.EMS_v2.dtos;

import jakarta.persistence.*;

public class PositionDto {
    private Long id;
    private String title;
    private Long departmentId;
    private String departmentName;

    // ---- Constructor ----
    public PositionDto(Long id, String title, Long departmentId, String departmentName) {
        this.id = id;
        this.title = title;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // ---- Getters ----
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
}

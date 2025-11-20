package com.example.EMS_v2.dtos;

public class DepartmentDto {
    private Long id;
    private String name;

    // ---- Constructor ----
    public DepartmentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // ---- Getters ----
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

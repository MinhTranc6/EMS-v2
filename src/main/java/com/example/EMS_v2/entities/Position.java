package com.example.EMS_v2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "department")
    private String departmentName;

    // ---- Constructor ----
    protected Position(){}
    public Position(Long id, String title, Long departmentId, String departmentName) {
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
    // ---- Setters ----
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

package com.example.EMS_v2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class EmployeeDto {
    private Long id;
    private String name;
    private String departmentName;
    private String positionTitle;
    private String contact;
    private String hireDate;
    private BigDecimal negotiatedSalary;

    // ---- Constructor ----
    public EmployeeDto(Long id, String name, String positionTitle, String departmentName, String contact, String hireDate, BigDecimal negotiatedSalary) {
        this.id = id;
        this.name = name;
        this.positionTitle = positionTitle;
        this.departmentName = departmentName;
        this.contact = contact;
        this.hireDate = hireDate;
        this.negotiatedSalary = negotiatedSalary;
    }
    // ---- Getters ----
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPositionTitle() {
        return positionTitle;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public String getContact() {
        return contact;
    }
    public String getHireDate() {
        return hireDate;
    }
    public BigDecimal getNegotiatedSalary() {
        return negotiatedSalary;
    }
}

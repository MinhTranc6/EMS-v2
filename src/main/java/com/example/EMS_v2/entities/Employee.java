package com.example.EMS_v2.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "department")
    private String departmentName;
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "position_title")
    private String positionTitle;
    private String contact;
    private String hireDate;
    private BigDecimal negotiatedSalary;

    // ---- Constructor ----
    protected Employee() {}

    public static Employee create() {
        return new Employee();
    }

    public Employee(Long id, String name, Long departmentId, String departmentName, Long positionId, String positionTitle, String contact, String hireDate, BigDecimal negotiatedSalary) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.positionId = positionId;
        this.positionTitle = positionTitle;
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
    public Long getDepartmentId() {
        return departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public Long getPositionId() {
        return positionId;
    }
    public String getPositionTitle() {
        return positionTitle;
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

    // ---- Setters ----
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
    public void setNegotiatedSalary(BigDecimal negotiatedSalary) {
        this.negotiatedSalary = negotiatedSalary;
    }
}

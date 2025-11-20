package com.example.EMS_v2.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "date_applied")
    private String dateApplied;
    private String contact;
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "position_title")
    private String positionTitle;
    @Column(name = "expected_salary")
    private BigDecimal expectedSalary;
    private String status;
    @Column(name = "converted_to_employee")
    private Boolean convertedToEmployee;

    // ---- Constructor ----
    protected Applicant(){

    }

    public Applicant(Long id, String name, String dateApplied, String contact, Long positionId, String positionTitle, BigDecimal expectedSalary, String status, Boolean convertedToEmployee) {
        this.id = id;
        this.name = name;
        this.dateApplied = dateApplied;
        this.contact = contact;
        this.positionId = positionId;
        this.positionTitle = positionTitle;
        this.expectedSalary = expectedSalary;
        this.status = status;
        this.convertedToEmployee = convertedToEmployee;
    }

    // ---- Getter ----
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDateApplied() {
        return dateApplied;
    }
    public String getContact() {
        return contact;
    }
    public Long getPositionId() {
        return positionId;
    }
    public String getPositionTitle() {
        return positionTitle;
    }
    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }
    public String getStatus() {
        return status;
    }
    public Boolean getConvertedToEmployee() {
        return convertedToEmployee;
    }

    // ---- Setter ----
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }
    public void setExpectedSalary(BigDecimal expectedSalary) {
        this.expectedSalary = expectedSalary;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setConvertedToEmployee(Boolean convertedToEmployee) {
        this.convertedToEmployee = convertedToEmployee;
    }
}

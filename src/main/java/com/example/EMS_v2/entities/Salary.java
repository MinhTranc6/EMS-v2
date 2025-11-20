package com.example.EMS_v2.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payroll") // maps to the SQL Server table "payroll"
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "negotiated_amount")
    private BigDecimal negotiatedAmount;

    @Column(name = "work_days")
    private Integer workDays;

    @Column(name = "days_worked")
    private Integer daysWorked;

    @Column(name = "hours_worked")
    private Integer hoursWorked;

    @Column(name = "hours_overtime")
    private Integer hoursOverTime;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    // ---- Constructor ----
    protected Salary(){}
    public static Salary create(){
        return new Salary();
    }
    public Salary(Long id, BigDecimal negotiatedAmount, Integer workDays, Integer daysWorked, Integer hoursWorked, Integer hoursOverTime, Long employeeId) {
        this.id = id;
        this.negotiatedAmount = negotiatedAmount;
        this.workDays = workDays;
        this.daysWorked = daysWorked;
        this.hoursWorked = hoursWorked;
        this.hoursOverTime = hoursOverTime;
        this.employeeId = employeeId;
    }
    // ---- Getters ----
    public Long getId() {
        return id;
    }
    public BigDecimal getNegotiatedAmount() {
        return negotiatedAmount;
    }
    public Integer getWorkDays() {
        return workDays;
    }
    public Integer getDaysWorked() {
        return daysWorked;
    }
    public Integer getHoursWorked() {
        return hoursWorked;
    }
    public Integer getHoursOverTime() {
        return hoursOverTime;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    // ---- Setters ----
    public void setId(Long id) {
        this.id = id;
    }
    public void setNegotiatedAmount(BigDecimal negotiatedAmount) {
        this.negotiatedAmount = negotiatedAmount;
    }
    public void setWorkDays(Integer workDays) {
        this.workDays = workDays;
    }
    public void setDaysWorked(Integer daysWorked) {
        this.daysWorked = daysWorked;
    }
    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    public void setHoursOverTime(Integer hoursOverTime) {
        this.hoursOverTime = hoursOverTime;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
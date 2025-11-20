package com.example.EMS_v2.dtos;

import java.math.BigDecimal;

public class SalaryDto {
    private Long id;
    private BigDecimal negotiatedAmount;
    private Integer workDays;
    private Integer daysWorked;
    private Integer hoursWorked;
    private Integer hoursOverTime;
    private Long employeeId;

    public SalaryDto(Long id, BigDecimal negotiatedAmount, Integer workDays, Integer daysWorked, Integer hoursWorked, Integer hoursOverTime, Long employeeId) {
        this.id = id;
        this.negotiatedAmount = negotiatedAmount;
        this.workDays = workDays;
        this.daysWorked = daysWorked;
        this.hoursWorked = hoursWorked;
        this.hoursOverTime = hoursOverTime;
        this.employeeId = employeeId;
    }

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
}

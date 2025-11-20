package com.example.EMS_v2.dtos;



import java.math.BigDecimal;

public class ApplicantDto {
    private Long id;
    private String name;
    private String dateApplied;
    private String contact;
    private Long positionId;
    private String positionTitle;
    private BigDecimal expectedSalary;
    private String status;
    private Boolean convertedToEmployee;

    // ---- Constructor ----
    public ApplicantDto(Long id, String name, String dateApplied, String contact, Long positionId, String positionTitle, BigDecimal expectedSalary, String status, Boolean convertedToEmployee) {
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
}

package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.ApplicantDto;
import com.example.EMS_v2.entities.Applicant;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T15:40:08+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ApplicantMapperImpl implements ApplicantMapper {

    @Override
    public ApplicantDto toDto(Applicant applicant) {
        if ( applicant == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String dateApplied = null;
        String contact = null;
        Long positionId = null;
        String positionTitle = null;
        BigDecimal expectedSalary = null;
        String status = null;
        Boolean convertedToEmployee = null;

        id = applicant.getId();
        name = applicant.getName();
        dateApplied = applicant.getDateApplied();
        contact = applicant.getContact();
        positionId = applicant.getPositionId();
        positionTitle = applicant.getPositionTitle();
        expectedSalary = applicant.getExpectedSalary();
        status = applicant.getStatus();
        convertedToEmployee = applicant.getConvertedToEmployee();

        ApplicantDto applicantDto = new ApplicantDto( id, name, dateApplied, contact, positionId, positionTitle, expectedSalary, status, convertedToEmployee );

        return applicantDto;
    }

    @Override
    public Applicant toEntity(ApplicantDto applicantDto) {
        if ( applicantDto == null ) {
            return null;
        }

        Boolean convertedToEmployee = null;
        Long id = null;
        String name = null;
        String dateApplied = null;
        String contact = null;
        Long positionId = null;
        String positionTitle = null;
        BigDecimal expectedSalary = null;
        String status = null;

        if ( applicantDto.getConvertedToEmployee() != null ) {
            convertedToEmployee = applicantDto.getConvertedToEmployee();
        }
        else {
            convertedToEmployee = false;
        }
        id = applicantDto.getId();
        name = applicantDto.getName();
        dateApplied = applicantDto.getDateApplied();
        contact = applicantDto.getContact();
        positionId = applicantDto.getPositionId();
        positionTitle = applicantDto.getPositionTitle();
        expectedSalary = applicantDto.getExpectedSalary();
        status = applicantDto.getStatus();

        Applicant applicant = new Applicant( id, name, dateApplied, contact, positionId, positionTitle, expectedSalary, status, convertedToEmployee );

        return applicant;
    }

    @Override
    public void updateApplicantFromDto(ApplicantDto dto, Applicant entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getConvertedToEmployee() != null ) {
            entity.setConvertedToEmployee( dto.getConvertedToEmployee() );
        }
        else {
            entity.setConvertedToEmployee( false );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDateApplied() != null ) {
            entity.setDateApplied( dto.getDateApplied() );
        }
        if ( dto.getContact() != null ) {
            entity.setContact( dto.getContact() );
        }
        if ( dto.getPositionId() != null ) {
            entity.setPositionId( dto.getPositionId() );
        }
        if ( dto.getPositionTitle() != null ) {
            entity.setPositionTitle( dto.getPositionTitle() );
        }
        if ( dto.getExpectedSalary() != null ) {
            entity.setExpectedSalary( dto.getExpectedSalary() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
    }
}

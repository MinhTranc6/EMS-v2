package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.SalaryDto;
import com.example.EMS_v2.entities.Salary;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-31T17:17:51+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class SalaryMapperImpl implements SalaryMapper {

    @Override
    public SalaryDto toDto(Salary salary) {
        if ( salary == null ) {
            return null;
        }

        Long id = null;
        BigDecimal negotiatedAmount = null;
        Integer workDays = null;
        Integer daysWorked = null;
        Integer hoursWorked = null;
        Integer hoursOverTime = null;
        Long employeeId = null;

        id = salary.getId();
        negotiatedAmount = salary.getNegotiatedAmount();
        workDays = salary.getWorkDays();
        daysWorked = salary.getDaysWorked();
        hoursWorked = salary.getHoursWorked();
        hoursOverTime = salary.getHoursOverTime();
        employeeId = salary.getEmployeeId();

        SalaryDto salaryDto = new SalaryDto( id, negotiatedAmount, workDays, daysWorked, hoursWorked, hoursOverTime, employeeId );

        return salaryDto;
    }

    @Override
    public Salary toEntity(SalaryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Long id = null;
        BigDecimal negotiatedAmount = null;
        Integer workDays = null;
        Integer daysWorked = null;
        Integer hoursWorked = null;
        Integer hoursOverTime = null;
        Long employeeId = null;

        id = dto.getId();
        negotiatedAmount = dto.getNegotiatedAmount();
        workDays = dto.getWorkDays();
        daysWorked = dto.getDaysWorked();
        hoursWorked = dto.getHoursWorked();
        hoursOverTime = dto.getHoursOverTime();
        employeeId = dto.getEmployeeId();

        Salary salary = new Salary( id, negotiatedAmount, workDays, daysWorked, hoursWorked, hoursOverTime, employeeId );

        return salary;
    }

    @Override
    public void updateSalaryFromDto(SalaryDto dto, Salary entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNegotiatedAmount() != null ) {
            entity.setNegotiatedAmount( dto.getNegotiatedAmount() );
        }
        if ( dto.getWorkDays() != null ) {
            entity.setWorkDays( dto.getWorkDays() );
        }
        if ( dto.getDaysWorked() != null ) {
            entity.setDaysWorked( dto.getDaysWorked() );
        }
        if ( dto.getHoursWorked() != null ) {
            entity.setHoursWorked( dto.getHoursWorked() );
        }
        if ( dto.getHoursOverTime() != null ) {
            entity.setHoursOverTime( dto.getHoursOverTime() );
        }
    }
}

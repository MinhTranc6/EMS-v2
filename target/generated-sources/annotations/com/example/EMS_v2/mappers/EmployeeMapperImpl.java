package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.EmployeeDto;
import com.example.EMS_v2.entities.Employee;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T15:40:08+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String positionTitle = null;
        String departmentName = null;
        String contact = null;
        String hireDate = null;
        BigDecimal negotiatedSalary = null;

        id = employee.getId();
        name = employee.getName();
        positionTitle = employee.getPositionTitle();
        departmentName = employee.getDepartmentName();
        contact = employee.getContact();
        hireDate = employee.getHireDate();
        negotiatedSalary = employee.getNegotiatedSalary();

        EmployeeDto employeeDto = new EmployeeDto( id, name, positionTitle, departmentName, contact, hireDate, negotiatedSalary );

        return employeeDto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String departmentName = null;
        String positionTitle = null;
        String contact = null;
        String hireDate = null;
        BigDecimal negotiatedSalary = null;

        id = dto.getId();
        name = dto.getName();
        departmentName = dto.getDepartmentName();
        positionTitle = dto.getPositionTitle();
        contact = dto.getContact();
        hireDate = dto.getHireDate();
        negotiatedSalary = dto.getNegotiatedSalary();

        Long departmentId = null;
        Long positionId = null;

        Employee employee = new Employee( id, name, departmentId, departmentName, positionId, positionTitle, contact, hireDate, negotiatedSalary );

        return employee;
    }

    @Override
    public void updateEmployeeFromDto(EmployeeDto dto, Employee entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDepartmentName() != null ) {
            entity.setDepartmentName( dto.getDepartmentName() );
        }
        if ( dto.getPositionTitle() != null ) {
            entity.setPositionTitle( dto.getPositionTitle() );
        }
        if ( dto.getContact() != null ) {
            entity.setContact( dto.getContact() );
        }
        if ( dto.getHireDate() != null ) {
            entity.setHireDate( dto.getHireDate() );
        }
        if ( dto.getNegotiatedSalary() != null ) {
            entity.setNegotiatedSalary( dto.getNegotiatedSalary() );
        }
    }
}

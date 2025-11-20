package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.DepartmentDto;
import com.example.EMS_v2.entities.Department;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T15:40:07+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto toDto(Department department) {
        if ( department == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = department.getId();
        name = department.getName();

        DepartmentDto departmentDto = new DepartmentDto( id, name );

        return departmentDto;
    }

    @Override
    public Department toEntity(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = departmentDto.getId();
        name = departmentDto.getName();

        Department department = new Department( id, name );

        return department;
    }

    @Override
    public void updateDepartmentFromDto(DepartmentDto departmentDto, Department entity) {
        if ( departmentDto == null ) {
            return;
        }

        if ( departmentDto.getName() != null ) {
            entity.setName( departmentDto.getName() );
        }
    }
}

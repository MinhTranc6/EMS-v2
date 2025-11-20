package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.DepartmentDto;
import com.example.EMS_v2.entities.Department;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);

    Department toEntity(DepartmentDto departmentDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDepartmentFromDto(DepartmentDto departmentDto, @MappingTarget Department entity);
}
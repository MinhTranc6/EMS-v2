package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.EmployeeDto;
import com.example.EMS_v2.entities.Employee;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "positionId", ignore = true)
    Employee toEntity(EmployeeDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "positionId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee entity);

}

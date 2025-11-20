package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.SalaryDto;
import com.example.EMS_v2.entities.Salary;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SalaryMapper {
    SalaryDto toDto(Salary salary);

    Salary toEntity(SalaryDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSalaryFromDto(SalaryDto dto, @MappingTarget Salary entity);
}

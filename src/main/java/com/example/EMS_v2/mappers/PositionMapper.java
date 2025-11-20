package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.PositionDto;
import com.example.EMS_v2.entities.Position;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionDto toDto(Position position);

    Position toEntity(PositionDto positionDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePositionFromDto(PositionDto positionDto, @MappingTarget Position entity);
}

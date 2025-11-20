package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.ApplicantDto;
import com.example.EMS_v2.entities.Applicant;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface ApplicantMapper {
    ApplicantDto toDto(Applicant applicant);

    @Mapping(target = "convertedToEmployee", defaultValue = "false")
    Applicant toEntity(ApplicantDto applicantDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "convertedToEmployee", defaultValue = "false")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateApplicantFromDto(ApplicantDto dto, @MappingTarget Applicant entity);
}

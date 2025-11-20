package com.example.EMS_v2.mappers;

import com.example.EMS_v2.dtos.PositionDto;
import com.example.EMS_v2.entities.Position;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T15:40:08+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public PositionDto toDto(Position position) {
        if ( position == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        Long departmentId = null;
        String departmentName = null;

        id = position.getId();
        title = position.getTitle();
        departmentId = position.getDepartmentId();
        departmentName = position.getDepartmentName();

        PositionDto positionDto = new PositionDto( id, title, departmentId, departmentName );

        return positionDto;
    }

    @Override
    public Position toEntity(PositionDto positionDto) {
        if ( positionDto == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        Long departmentId = null;
        String departmentName = null;

        id = positionDto.getId();
        title = positionDto.getTitle();
        departmentId = positionDto.getDepartmentId();
        departmentName = positionDto.getDepartmentName();

        Position position = new Position( id, title, departmentId, departmentName );

        return position;
    }

    @Override
    public void updatePositionFromDto(PositionDto positionDto, Position entity) {
        if ( positionDto == null ) {
            return;
        }

        if ( positionDto.getTitle() != null ) {
            entity.setTitle( positionDto.getTitle() );
        }
        if ( positionDto.getDepartmentId() != null ) {
            entity.setDepartmentId( positionDto.getDepartmentId() );
        }
        if ( positionDto.getDepartmentName() != null ) {
            entity.setDepartmentName( positionDto.getDepartmentName() );
        }
    }
}

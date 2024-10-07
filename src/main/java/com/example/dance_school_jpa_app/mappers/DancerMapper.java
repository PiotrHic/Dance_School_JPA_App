package com.example.dance_school_jpa_app.mappers;

import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.domain.DancerDTO;
import org.springframework.stereotype.Component;

@Component
public class DancerMapper {

    public DancerDTO dancerToDancerDTO(Dancer dancer){
        return DancerDTO.builder()
                .id(dancer.getId())
                .createdAt(dancer.getCreatedAt())
                .createdBy(dancer.getCreatedBy())
                .name(dancer.getName())
                .lastModifiedBy(dancer.getLastModifiedBy())
                .danceCourses(dancer.getDanceCourses())
                .lastModifiedAt(dancer.getLastModifiedAt())
                .build();
    }

    public Dancer dancerDTOToDancer(DancerDTO dancerDTO){
        return Dancer.builder()
                .id(dancerDTO.getId())
                .createdAt(dancerDTO.getCreatedAt())
                .createdBy(dancerDTO.getCreatedBy())
                .name(dancerDTO.getName())
                .lastModifiedBy(dancerDTO.getLastModifiedBy())
                .danceCourses(dancerDTO.getDanceCourses())
                .lastModifiedAt(dancerDTO.getLastModifiedAt())
                .build();
    }
}

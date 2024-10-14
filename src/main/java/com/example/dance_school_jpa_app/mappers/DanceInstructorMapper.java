package com.example.dance_school_jpa_app.mappers;

import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.dto.DanceInstructorDTO;
import com.example.dance_school_jpa_app.dto.DancerDTO;
import org.springframework.stereotype.Component;


@Component
public class DanceInstructorMapper {

    public DanceInstructorDTO dancerToDancerDTO(DanceInstructor danceInstructor){
        return DanceInstructorDTO.builder()
                .id(danceInstructor.getId())
                .createdAt(danceInstructor.getCreatedAt())
                .createdBy(danceInstructor.getCreatedBy())
                .name(danceInstructor.getName())
                .lastModifiedBy(danceInstructor.getLastModifiedBy())
                .danceCourse(danceInstructor.getDanceCourse())
                .lastModifiedAt(danceInstructor.getLastModifiedAt())
                .build();
    }

    public DanceInstructor dancerDTOToDancer(DanceInstructorDTO danceInstructorDTO){
        return DanceInstructor.builder()
                .id(danceInstructorDTO.getId())
                .createdAt(danceInstructorDTO.getCreatedAt())
                .createdBy(danceInstructorDTO.getCreatedBy())
                .name(danceInstructorDTO.getName())
                .lastModifiedBy(danceInstructorDTO.getLastModifiedBy())
                .danceCourse(danceInstructorDTO.getDanceCourse())
                .lastModifiedAt(danceInstructorDTO.getLastModifiedAt())
                .build();
    }
}

package com.example.dance_school_jpa_app.mappers;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.dto.DanceCourseDTO;
import com.example.dance_school_jpa_app.dto.DanceInstructorDTO;
import org.springframework.stereotype.Component;

@Component
public class DanceCourseMapper {



    public DanceCourseDTO danceCourseToDanceCourseDTO(DanceCourse danceCourse){
        return DanceCourseDTO.builder()
                .id(danceCourse.getId())
                .createdAt(danceCourse.getCreatedAt())
                .createdBy(danceCourse.getCreatedBy())
                .name(danceCourse.getName())
                .lastModifiedBy(danceCourse.getLastModifiedBy())
                .danceInstructors(danceCourse.getDanceInstructors())
                .dancers(danceCourse.getDancers())
                .lastModifiedAt(danceCourse.getLastModifiedAt())
                .build();
    }

    public DanceCourse danceCourseDTOToDanceCourse(DanceCourseDTO danceCourseDTO){
        return DanceCourse.builder()
                .id(danceCourseDTO.getId())
                .createdAt(danceCourseDTO.getCreatedAt())
                .createdBy(danceCourseDTO.getCreatedBy())
                .name(danceCourseDTO.getName())
                .lastModifiedBy(danceCourseDTO.getLastModifiedBy())
                .danceInstructors(danceCourseDTO.getDanceInstructors())
                .dancers(danceCourseDTO.getDancers())
                .lastModifiedAt(danceCourseDTO.getLastModifiedAt())
                .build();
    }
}

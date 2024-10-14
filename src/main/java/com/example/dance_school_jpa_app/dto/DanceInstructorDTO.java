package com.example.dance_school_jpa_app.dto;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DanceInstructorDTO extends BaseEntityDTO{

    private DanceCourse danceCourse;

}

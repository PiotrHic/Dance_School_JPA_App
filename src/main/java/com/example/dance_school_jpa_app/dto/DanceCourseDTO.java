package com.example.dance_school_jpa_app.dto;

import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.domain.Dancer;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DanceCourseDTO extends BaseEntityDTO{

    private List<Dancer> dancers;
    private List<DanceInstructor> danceInstructors;

}

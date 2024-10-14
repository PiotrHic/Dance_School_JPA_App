package com.example.dance_school_jpa_app.dto;

import com.example.dance_school_jpa_app.domain.BaseEntity;
import com.example.dance_school_jpa_app.domain.DanceCourse;
import jakarta.persistence.*;
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
public class DancerDTO extends BaseEntityDTO {

    private List<DanceCourse> danceCourses;


}

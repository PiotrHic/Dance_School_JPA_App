package com.example.dance_school_jpa_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
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
public class DancerDTO extends BaseEntity{

    private Integer id;
    private String name;
    private List<DanceCourse> danceCourses;

}

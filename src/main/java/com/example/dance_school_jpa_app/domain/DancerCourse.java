package com.example.dance_school_jpa_app.domain;

import jakarta.persistence.*;
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
@Table(name = "Dancer_Course_Table")
@Entity
public class DancerCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Dancer_Course_Table_generator")
    @SequenceGenerator(name = "Dancer_Course_Table_generator", sequenceName = "Dancer_Course_Table_generator")
    @Column(name = "dancer_course_id", nullable = false)
    private Integer id;

    @Column(name = "dancer_course_name", nullable = false, unique = true)
    private String name;

}

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
@Table(name = "Dance_Course_Instructor_Table")
@Entity
public class DanceInstructor extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Dance_Instructor_Table_generator")
    @SequenceGenerator(name = "Dance_Instructor_Table_generator", sequenceName = "Dance_Instructor_Table_generator")
    @Column(name = "dance_instructor_id", nullable = false)
    private Integer id;

    @Column(name = "dance_instructor_name", nullable = false, unique = true)
    private String name;

}
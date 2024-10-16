package com.example.dance_school_jpa_app.domain;

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
@Table(name = "Dancer_Table")
@Entity
public class Dancer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dancer_id", nullable = false)
    private Integer id;

    @Column(name = "dancer_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "dancers")
    private List<DanceCourse> danceCourses;

}

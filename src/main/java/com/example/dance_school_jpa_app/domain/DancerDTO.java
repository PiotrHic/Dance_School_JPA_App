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
public class DancerDTO extends BaseEntity{

//    private Integer id;
//    private String name;
//    private List<DanceCourse> danceCourses;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "dancer_id", nullable = false)
private Integer id;

    @Column(name = "dancer_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "dancers")
    private List<DanceCourse> danceCourses;


}

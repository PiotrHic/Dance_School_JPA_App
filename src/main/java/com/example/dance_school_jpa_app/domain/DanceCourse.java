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
@Table(name = "Dance_Course_Table")
@Entity
public class DanceCourse extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Dance_Course_Table_generator")
    @SequenceGenerator(name = "Dance_Course_Table_generator", sequenceName = "Dance_Course_Table_generator")
    @Column(name = "dance_course_id",nullable = false)
    private Integer id;

    @Column(name = "dance_course_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "Dance_Course_And_Dancer_Table",
            joinColumns = @JoinColumn(
                    name = "dance_course_joined_id",
                    referencedColumnName = "dance_course_id"
            ),
                    inverseJoinColumns = @JoinColumn(
                            name = "dancer_joined_id",
                            referencedColumnName = "dancer_id"
                    )
    )
    private List<Dancer> dancers;

    @OneToMany(mappedBy = "danceCourse", fetch = FetchType.EAGER)
    private List<DanceInstructor> danceInstructors;

}

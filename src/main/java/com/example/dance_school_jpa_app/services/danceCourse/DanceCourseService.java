package com.example.dance_school_jpa_app.services.danceCourse;

import com.example.dance_school_jpa_app.domain.DanceCourse;

import java.util.List;

public interface DanceCourseService {

    DanceCourse createDanceCourse(DanceCourse danceCourse);

    DanceCourse getDanceCourse (Integer id);
    List<DanceCourse> getAllDanceCourses();

    DanceCourse updateDanceCourse (Integer id, DanceCourse danceCourse);

    String deleteDanceCourse (Integer id);
    void deleteAllDanceCourses ();
}

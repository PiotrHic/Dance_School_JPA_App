package com.example.dance_school_jpa_app.services;

import com.example.dance_school_jpa_app.domain.DanceCourse;

import java.util.List;

public interface DanceCourseService {

    DanceCourse createDancer(DanceCourse danceCourse);

    DanceCourse getDancer (Integer id);
    List<DanceCourse> getAllDancers();

    DanceCourse updateDancer (Integer id, DanceCourse danceCourse);

    DanceCourse deleteDancer (Integer id);
    void deleteAllDancers ();
}

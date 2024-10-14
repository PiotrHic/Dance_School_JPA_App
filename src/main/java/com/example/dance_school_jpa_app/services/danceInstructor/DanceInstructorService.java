package com.example.dance_school_jpa_app.services.danceInstructor;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.DanceInstructor;

import java.util.List;

public interface DanceInstructorService {

    DanceInstructor createDanceInstructor(DanceInstructor danceInstructor);

    DanceInstructor getDanceInstructor (Integer id);
    List<DanceInstructor> getAllDanceInstructors();

    DanceInstructor updateDanceInstructor (Integer id, DanceInstructor danceInstructor);

    String deleteDanceInstructor (Integer id);
    void deleteAllDanceInstructors();
}

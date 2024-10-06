package com.example.dance_school_jpa_app.repositories;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.domain.Dancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanceCourseRepository extends JpaRepository<DanceCourse, Integer> {

    DanceInstructor findDanceCourseByName(String name);
    DanceInstructor deleteDanceCourseByName(String name);
}

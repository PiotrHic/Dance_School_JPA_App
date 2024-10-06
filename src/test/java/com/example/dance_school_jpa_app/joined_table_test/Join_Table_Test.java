package com.example.dance_school_jpa_app.joined_table_test;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class Join_Table_Test {

    @Autowired
    DancerRepository dancerRepository;

    @Autowired
    DanceCourseRepository danceCourseRepository;

    @Test
    void testTable(){
        Dancer dancer = Dancer.builder()
                .name("test2")
                .createdAt(LocalDateTime.MIN)
                .lastModifiedAt(LocalDateTime.MIN)
                .createdBy("test1")
                .lastModifiedBy("test1")
                .build();

        DanceCourse dance_course = DanceCourse.builder()
                .name("test2")
                .createdAt(LocalDateTime.MIN)
                .lastModifiedAt(LocalDateTime.MIN)
                .createdBy("test1")
                .lastModifiedBy("test1")
                .build();

        dancerRepository.save(dancer);
        danceCourseRepository.save(dance_course);


    }
}

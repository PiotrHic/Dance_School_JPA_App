package com.example.dance_school_jpa_app.repository;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class DanceCourseRepositoryTest {
    @Autowired
    DanceCourseRepository danceCourseRepository;

    DanceCourse first = DanceCourse.builder()
            .name("test1")
            .createdAt(LocalDateTime.MIN)
            .lastModifiedAt(LocalDateTime.MIN)
            .createdBy("test1")
            .lastModifiedBy("test1")
            .build();

    DanceCourse second = DanceCourse.builder()
            .name("test2")
            .createdAt(LocalDateTime.MAX)
            .lastModifiedAt(LocalDateTime.MAX)
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();

    @BeforeEach
    void setUp(){
        danceCourseRepository.deleteAll();
    }


    @Test
    void createDanceCourse(){
        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        danceCourseRepository.save(first);

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void getAllDanceCourses(){
        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        danceCourseRepository.save(first);
        danceCourseRepository.save(second);

        count = danceCourseRepository.findAll().size();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneDanceCourseById(){

        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        DanceCourse saved = danceCourseRepository.save(first);

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(1);

        DanceCourse founded = danceCourseRepository.getReferenceById(first.getId());

        assertThat(founded.getName()).isEqualTo("test1");


    }

    @Test
    void deleteDanceCourseById(){

        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        danceCourseRepository.save(first);

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(1);

        danceCourseRepository.deleteById(first.getId());

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllDanceCoursesId() {
        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        danceCourseRepository.save(first);
        danceCourseRepository.save(second);

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(2);

        danceCourseRepository.deleteAll();

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

    }

    @Test
    void getDanceCourseByName(){
        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        DanceCourse saved = danceCourseRepository.save(first);

//        Dancer foundByName = danceCourseRepositoryfindDancerByName(saved.getName());

        assertThat(first.getName()).isEqualTo(saved.getName());
    }

    @Disabled
    @Test
    void deleteDanceCourseByName(){
        long count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);

        DanceCourse saved = danceCourseRepository.save(first);

        String name = saved.getName();

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(1);

//        Dancer deletedByName = danceCourseRepository.deleteByName(name);

        count = danceCourseRepository.count();

        assertThat(count).isEqualTo(0);
    }

}

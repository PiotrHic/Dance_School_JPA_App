package com.example.dance_school_jpa_app.repository;

import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.repositories.DanceInstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class DanceInstructorTest {

    @Autowired
    DanceInstructorRepository danceInstructorRepository;

    DanceInstructor first = DanceInstructor.builder()
            .name("test1")
            .createdAt(LocalDateTime.MIN)
            .lastModifiedAt(LocalDateTime.MIN)
            .createdBy("test1")
            .lastModifiedBy("test1")
            .build();

    DanceInstructor second = DanceInstructor.builder()
            .name("test2")
            .createdAt(LocalDateTime.MAX)
            .lastModifiedAt(LocalDateTime.MAX)
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();

    @BeforeEach
    void setUp(){
        danceInstructorRepository.deleteAll();
    }


    @Test
    void createDanceInstructor(){
        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        danceInstructorRepository.save(first);

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void getAllDanceInstructors(){
        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        danceInstructorRepository.save(first);
        danceInstructorRepository.save(second);

        count = danceInstructorRepository.findAll().size();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneDanceInstructorById(){

        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        DanceInstructor saved = danceInstructorRepository.save(first);

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(1);

        DanceInstructor founded = danceInstructorRepository.getReferenceById(first.getId());

        assertThat(founded.getName()).isEqualTo("test1");


    }

    @Test
    void deleteDanceInstructorById(){

        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        danceInstructorRepository.save(first);

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(1);

        danceInstructorRepository.deleteById(first.getId());

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllDancersId() {
        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        danceInstructorRepository.save(first);
        danceInstructorRepository.save(second);

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(2);

        danceInstructorRepository.deleteAll();

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

    }

    @Test
    void getDancerByName(){
        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        DanceInstructor saved = danceInstructorRepository.save(first);

        DanceInstructor  foundByName = danceInstructorRepository.findDanceInstructorByName(saved.getName());

        assertThat(first.getName()).isEqualTo(saved.getName());
    }

    @Disabled
    @Test
    void deleteDancerByName(){
        long count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        DanceInstructor  saved = danceInstructorRepository.save(first);

        String name = saved.getName();

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(1);

        DanceInstructor   deletedByName = danceInstructorRepository.deleteDanceInstructorByName(name);

        count = danceInstructorRepository.count();

        assertThat(count).isEqualTo(0);
    }
}

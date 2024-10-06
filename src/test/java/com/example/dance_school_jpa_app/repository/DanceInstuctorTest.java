package com.example.dance_school_jpa_app.repository;

import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class DanceInstuctorTest {

    @Autowired
    DanceInstructorRepository dancerInstructorRepository;

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
        dancerInstructorRepository.deleteAll();
    }


    @Test
    void createDancer(){
        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        dancerInstructorRepository.save(first);

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void getAllDancers(){
        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        dancerInstructorRepository.save(first);
        dancerInstructorRepository.save(second);

        count = dancerInstructorRepository.findAll().size();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneDancerById(){

        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        Dancer saved = dancerInstructorRepository.save(first);

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(1);

        Dancer founded = dancerInstructorRepository.getReferenceById(first.getId());

        assertThat(founded.getName()).isEqualTo("test1");


    }

    @Test
    void deleteDancerById(){

        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        dancerInstructorRepository.save(first);

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(1);

        dancerInstructorRepository.deleteById(first.getId());

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllDancersId() {
        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        dancerInstructorRepository.save(first);
        dancerInstructorRepository.save(second);

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(2);

        dancerInstructorRepository.deleteAll();

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

    }

    @Test
    void getDancerByName(){
        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        Dancer saved = dancerInstructorRepository.save(first);

        Dancer foundByName = dancerInstructorRepository.findDancerByName(saved.getName());

        assertThat(first.getName()).isEqualTo(saved.getName());
    }

    @Disabled
    @Test
    void deleteDancerByName(){
        long count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);

        Dancer saved = dancerInstructorRepository.save(first);

        String name = saved.getName();

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(1);

        Dancer deletedByName = dancerInstructorRepository.deleteByName(name);

        count = dancerInstructorRepository.count();

        assertThat(count).isEqualTo(0);
    }
}

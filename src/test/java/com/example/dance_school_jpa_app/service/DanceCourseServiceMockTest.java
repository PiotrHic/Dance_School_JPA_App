package com.example.dance_school_jpa_app.service;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.services.danceCourse.DanceCourseServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DanceCourseServiceMockTest {

    @Mock
    DanceCourseRepository danceCourseRepository;

    AutoCloseable autoCloseable;
    @InjectMocks
    DanceCourseServiceImpl danceCourseService;

    DanceCourse returned = DanceCourse.builder()
            .name("test")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test1")
            .lastModifiedBy("test1")
            .build();

    DanceCourse first = DanceCourse.builder()
            .name("test1")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    DanceCourse second = DanceCourse.builder()
            .name("test2")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();


    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        danceCourseService = new DanceCourseServiceImpl(danceCourseRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testMock(){
        Map mapMock = mock(Map.class);

        assertThat(mapMock.size()).isEqualTo(0);
    }


    @Test
    void createDanceCourse (){

        //stub the data
        when(danceCourseRepository.save(returned)).thenReturn(returned);

        DanceCourse result = danceCourseService.createDanceCourse(returned);
        Assertions.assertEquals("test", result.getName());
    }

    @Disabled
    @Test
    public void getOneDanceCourse() throws Exception{

        //stub the data
        when(danceCourseRepository.getReferenceById(returned.getId())).thenReturn(returned);
        DanceCourse result = danceCourseService.getDanceCourse(returned.getId());
        Assertions.assertEquals("test", result.getName());
    }

    @Test
    void getAllDanceCourse(){
        // when
        when(danceCourseRepository.findAll()).
                thenReturn(Arrays.asList(first,
                        second));

        //then
        List< DanceCourse> dancers = danceCourseService.getAllDanceCourses();
        Assertions.assertEquals(dancers.size(), 2);
    }

    @Disabled
    @Test
    void updateDanceCourse(){


        //stub the data
        when(danceCourseRepository.getReferenceById(first.getId())).thenReturn(first);
        when(danceCourseRepository.save(first)).thenReturn(first);

        danceCourseService.createDanceCourse(first);

        DanceCourse result = danceCourseService.updateDanceCourse(second.getId(), second);
        Assertions.assertEquals("test2", result.getName());

    }


    @Disabled
    @Test
    void deleteDanceCourse(){

        when(danceCourseRepository.getReferenceById(returned.getId())).thenReturn(returned);
        String result = danceCourseService.deleteDanceCourse(returned.getId());
        Assertions.assertEquals(result, isNotNull());
    }
}

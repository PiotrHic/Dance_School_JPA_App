package com.example.dance_school_jpa_app.service;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.repositories.DanceInstructorRepository;
import com.example.dance_school_jpa_app.services.DanceCourseServiceImpl;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DanceInstructorServiceMockTest {

    @Mock
    DanceInstructorRepository danceInstructorRepository;

    AutoCloseable autoCloseable;
    @InjectMocks
    DanceInstructorServiceImpl danceInstructorService;

    DanceInstructor returned = DanceInstructor.builder()
            .name("test")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test1")
            .lastModifiedBy("test1")
            .build();

    DanceInstructor first = DanceInstructor.builder()
            .name("test1")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    DanceInstructor second = DanceInstructor.builder()
            .name("test2")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();


    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        danceInstructorService = new DanceInstructorServiceImpl(danceInstructorRepository);
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
        when(danceInstructorRepository.save(returned)).thenReturn(returned);

        DanceCourse result = danceInstructorService.createDanceCourse(returned);
        Assertions.assertEquals("test", result.getName());
    }

    @Disabled
    @Test
    public void getOneDanceCourse() throws Exception{

        //stub the data
        when(danceInstructorRepository.getReferenceById(returned.getId())).thenReturn(returned);
        DanceCourse result = danceInstructorService.getDanceCourse(returned.getId());
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
        DanceCourse result = danceCourseService.deleteDanceCourse(returned.getId());
        Assertions.assertEquals("test", result.getName());
    }
}

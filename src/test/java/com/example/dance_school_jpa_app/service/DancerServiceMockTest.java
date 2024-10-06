package com.example.dance_school_jpa_app.service;

import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import com.example.dance_school_jpa_app.services.DancerServiceImpl;
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
public class DancerServiceMockTest {

    @Mock
    DancerRepository dancerRepository;

    AutoCloseable autoCloseable;
    @InjectMocks
    DancerServiceImpl dancerService;

    Dancer returned = Dancer.builder()
            .name("test")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test1")
            .lastModifiedBy("test1")
            .build();

    Dancer first = Dancer.builder()
            .name("test1")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    Dancer second = Dancer.builder()
            .name("test2")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();


    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        dancerService = new DancerServiceImpl(dancerRepository);
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
    void createDancer (){

        //stub the data
        when(dancerRepository.save(returned)).thenReturn(returned);

        Dancer result = dancerService.createDancer(returned);
        Assertions.assertEquals("test", result.getName());
    }

    @Disabled
    @Test
    public void getOneDancer() throws Exception{

        //stub the data
        when(dancerRepository.getReferenceById(returned.getId())).thenReturn(returned);
        Dancer result = dancerService.getDancer(returned.getId());
        Assertions.assertEquals("test", result.getName());
    }

    @Test
    void getAllDancers(){
        // when
        when(dancerRepository.findAll()).
                thenReturn(Arrays.asList(first,
                        second));

        //then
        List<Dancer> dancers = dancerService.getAllDancers();
        Assertions.assertEquals(dancers.size(), 2);
    }

    @Disabled
    @Test
    void updateDancer(){


        //stub the data
        when(dancerRepository.getReferenceById(first.getId())).thenReturn(first);
        when(dancerRepository.save(first)).thenReturn(first);

        dancerService.createDancer(first);

        Dancer result = dancerService.updateDancer(second.getId(), second);
        Assertions.assertEquals("test2", result.getName());

    }

    @Disabled
    @Test
    void deleteDancer(){

        when(dancerRepository.getReferenceById(returned.getId())).thenReturn(returned);
        Dancer result = dancerService.deleteDancer(returned.getId());
        Assertions.assertEquals("test", result.getName());
    }

}

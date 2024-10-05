package com.example.dance_school_jpa_app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DanceInstructorTest {

    DanceInstructor danceInstructor = DanceInstructor.builder()
            .id(1)
            .name("test").build();
    @Test
    public void setId(){
        //given
        Integer expected = 10;
        //when
        danceInstructor.setId(expected);
        //then
        Assertions.assertEquals(expected, danceInstructor.getId());
    }

    @Test
    public void getId(){
        //given
        Integer expected = 10;
        //when
        danceInstructor.setId(expected);
        //then
        Assertions.assertEquals(expected, danceInstructor.getId());
    }

    @Test
    public void setName(){
        //given
        String expected = "name";
        //when
        danceInstructor.setName(expected);
        //then
        Assertions.assertEquals(expected, danceInstructor.getName());
    }

    @Test
    public void getName(){
        //given
        String expected = "name";
        //when
        danceInstructor.setName(expected);
        //then
        Assertions.assertEquals(expected, danceInstructor.getName());
    }
}

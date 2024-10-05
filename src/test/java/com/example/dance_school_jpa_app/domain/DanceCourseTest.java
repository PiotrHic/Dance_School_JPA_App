package com.example.dance_school_jpa_app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DanceCourseTest {

    DanceCourse dancerCourse = DanceCourse.builder()
            .id(1)
            .name("test").build();
    @Test
    public void setId(){
        //given
        Integer expected = 10;
        //when
        dancerCourse.setId(expected);
        //then
        Assertions.assertEquals(expected, dancerCourse.getId());
    }

    @Test
    public void getId(){
        //given
        Integer expected = 10;
        //when
        dancerCourse.setId(expected);
        //then
        Assertions.assertEquals(expected, dancerCourse.getId());
    }

    @Test
    public void setName(){
        //given
        String expected = "name";
        //when
        dancerCourse.setName(expected);
        //then
        Assertions.assertEquals(expected, dancerCourse.getName());
    }

    @Test
    public void getName(){
        //given
        String expected = "name";
        //when
        dancerCourse.setName(expected);
        //then
        Assertions.assertEquals(expected, dancerCourse.getName());
    }

}

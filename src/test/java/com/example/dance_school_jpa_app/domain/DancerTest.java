package com.example.dance_school_jpa_app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DancerTest {

    Dancer dancer = Dancer.builder()
            .id(1)
            .name("test").build();

    @Test
    public void setId(){
        // given
        Integer expected = 10;
        // when
        dancer.setId(expected);
        // then
        Assertions.assertEquals(expected, dancer.getId());
    }

    @Test
    public void getId(){
        // given
        Integer expected = 10;
        // when
        dancer.setId(expected);
        // then
        Assertions.assertEquals(expected, dancer.getId());
    }

    @Test
    public void setName(){
        // given
        String expected = "name";
        // when
        dancer.setName(expected);
        // then
        Assertions.assertEquals(expected, dancer.getName());
    }

    @Test
    public void getName(){
        // given
        String expected = "name";
        // when
        dancer.setName(expected);
        // then
        Assertions.assertEquals(expected, dancer.getName());
    }
}

package com.example.dance_school_jpa_app.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BaseEntityTest {

    BaseEbity baseEmtity = BaseEntity.builder
                            .id(1)
                            .name("test")
                            .build();

    @Test
    public void setId(){
        // given
        Integer expected = 20;
        // when
        baseEmtity.setId(20);
        // then
        Assert.assertEquals(expected, baseEmtity.getId());
    }

    @Test
    public void getId(){
        // given
        Integer expected =20;
        // when
        baseEmtity.setId(20);
        // then
        Assert.assertEquals(expected, baseEmtity.getId());
    }

    @Test
    public void setName(){
        // given
        String expected = "name";
        // when
        baseEmtity.setName(expected);
        // then
        Assert.assertEquals(expected, baseEmtity.getName());
    }

    @Test
    public void getName(){
        // given
        String expected = "name";
        // when
        baseEmtity.setName(expected);
        // then
        Assert.assertEquals(expected, baseEmtity.getName());
    }
}

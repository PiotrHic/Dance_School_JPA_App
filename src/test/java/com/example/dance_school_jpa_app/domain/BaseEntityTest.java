package com.example.dance_school_jpa_app.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class BaseEntityTest {

    BaseEntity baseEmtity = BaseEntity
            .builder()
            .createdAt(LocalDateTime.MIN)
            .lastModifiedAt(LocalDateTime.MIN)
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    @Test
    public void setCreationDate(){
        // given
        LocalDateTime expected = LocalDateTime.now();
        // when
        baseEmtity.setCreatedAt(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getCreatedAt());
    }

    @Test
    public void getCreationDate(){
        // given
        LocalDateTime expected = LocalDateTime.now();
        // when
        baseEmtity.setCreatedAt(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getCreatedAt());
    }

    @Test
    public void setModificationDate(){
        // given
        LocalDateTime expected = LocalDateTime.now();
        // when
        baseEmtity.setLastModifiedAt(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getLastModifiedAt());
    }

    @Test
    public void getModificationDate(){
        // given
        LocalDateTime expected = LocalDateTime.now();
        // when
        baseEmtity.setLastModifiedAt(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getLastModifiedAt());
    }

    @Test
    public void setAuthor(){
        // given
        String expected = "name";
        // when
        baseEmtity.setCreatedBy(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getCreatedBy());
    }

    @Test
    public void getAuthor(){
        // given
        String expected = "name";
        // when
        baseEmtity.setCreatedBy(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getCreatedBy());
    }

    @Test
    public void setModifier(){
        // given
        String expected = "name";
        // when
        baseEmtity.setLastModifiedBy(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getLastModifiedBy());
    }

    @Test
    public void getModifier(){
        // given
        String expected = "name";
        // when
        baseEmtity.setLastModifiedBy(expected);
        // then
        Assertions.assertEquals(expected, baseEmtity.getLastModifiedBy());
    }


}

package com.example.dance_school_jpa_app.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Dancer in Repository")
public class DancerNotFoundException extends EntityNotFoundException {

    private String message;
    public DancerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
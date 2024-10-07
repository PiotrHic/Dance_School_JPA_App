package com.example.dance_school_jpa_app.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Entity in Repository")
public class JPAEntityNotFoundException extends EntityNotFoundException {

    private String message;
    public JPAEntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

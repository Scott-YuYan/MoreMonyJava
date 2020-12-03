package com.example.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * More Money UserNoteFoundHandler
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ServiceException {

    public UserNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
    }
}

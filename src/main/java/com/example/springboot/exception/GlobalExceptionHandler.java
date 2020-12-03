package com.example.springboot.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundHandlerException(UserNotFoundException e){
        val response = ErrorResponse.builder()
                .statusCode(e.getStatusCode())
                .code("USER NOT FOUND!")
                .errorType(ServiceException.ErrorType.Client)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

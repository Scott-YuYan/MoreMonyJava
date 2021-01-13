package com.example.springboot.exception;

import lombok.val;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> userNotFoundHandlerException(ServiceException e) {
        val response = ErrorResponse.builder()
                .statusCode(e.getStatusCode())
                .code(e.getErrorCode())
                .errorType(e.getErrorType())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<?> incorrectCredentialsHandlerException(IncorrectCredentialsException e) {
        val response = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .code("INCORRECT_CREDENTIALS")
                .errorType(ServiceException.ErrorType.Client)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

}

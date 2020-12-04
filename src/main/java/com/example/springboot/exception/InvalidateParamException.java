package com.example.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidateParamException extends ServiceException {

    public InvalidateParamException(String message) {
        super(message);
        this.setErrorCode("INVALIDATE_PARAM！");
        this.setErrorType(ErrorType.Client);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
    }
}

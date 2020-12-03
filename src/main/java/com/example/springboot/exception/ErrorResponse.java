package com.example.springboot.exception;

import lombok.Builder;
import lombok.Data;

/**
 * 自定义的错误返回体
 */
@Data
@Builder
public class ErrorResponse {
    private int statusCode;
    private String code;
    private ServiceException.ErrorType errorType;
    private String message;
}

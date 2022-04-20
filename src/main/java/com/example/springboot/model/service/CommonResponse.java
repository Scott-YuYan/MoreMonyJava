package com.example.springboot.model.service;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 接口公共返回对象
 */
@Data
@AllArgsConstructor
public class CommonResponse <T>{

    /**
     * 响应code
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应结果
     */
    private T data;
}

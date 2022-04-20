package com.example.springboot.model.service;

import com.example.springboot.annotation.CheckProduct;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CheckProduct
@Accessors(chain = true) // 开启链式写法 区别于builder
public class Product {

    @NotEmpty(message = "产品code不能为空")
    private String productCode;

    @NotEmpty(message = "产品名称不能为空")
    private String productName;
}

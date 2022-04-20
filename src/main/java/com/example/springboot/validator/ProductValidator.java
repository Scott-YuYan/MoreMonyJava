package com.example.springboot.validator;

import com.example.springboot.annotation.CheckProduct;
import com.example.springboot.model.common.User;
import com.example.springboot.model.service.Product;
import org.hibernate.annotations.Check;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductValidator implements ConstraintValidator<CheckProduct, Product> {

    @Override
    public void initialize(CheckProduct constraintAnnotation) {

    }

    @Override
    public boolean isValid(Product value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if ("BTP".equals(value.getProductCode())) {
            String productName = value.getProductName();
            return productName.length() >= 4;
        }
        return true;
    }
}

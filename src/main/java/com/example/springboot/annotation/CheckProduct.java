package com.example.springboot.annotation;

import com.example.springboot.validator.ProductValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductValidator.class)
public @interface CheckProduct {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "";

}

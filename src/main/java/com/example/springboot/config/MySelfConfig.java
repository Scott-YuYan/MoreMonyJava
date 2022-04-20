package com.example.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "customer")
@PropertySource("classpath:customer.properties")
@Data
public class MySelfConfig {

    private String name;

    private String gender;

    private int age;
}

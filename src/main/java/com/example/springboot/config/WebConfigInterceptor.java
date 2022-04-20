package com.example.springboot.config;

import com.example.springboot.interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigInterceptor implements WebMvcConfigurer {

    DemoInterceptor demoInterceptor;

    @Autowired
    public WebConfigInterceptor(DemoInterceptor demoInterceptor) {
        this.demoInterceptor = demoInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor);
    }
}

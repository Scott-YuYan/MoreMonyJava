package com.example.springboot;

import com.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyController {
    @Autowired
    User user;

    @RequestMapping("/getUser")
    public String getUser(){
        return "通过properties文件配置用户名"+user.getName()+"性别"+user.getGender();
    }

    public static void main(String[] args) {
        SpringApplication.run(MyController.class, args);
    }
}

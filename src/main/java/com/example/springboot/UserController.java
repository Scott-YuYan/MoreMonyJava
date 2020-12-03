package com.example.springboot;

import com.example.springboot.converter.c2s.UserInfoConverter;
import com.example.springboot.exception.ErrorResponse;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.manager.UserInfoManager;
import com.example.springboot.model.service.User;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
public class UserController {
    private UserInfoManager userInfoManager;
    @Qualifier("c2s")
    private UserInfoConverter userInfoConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoConverter userInfoConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoConverter = userInfoConverter;
    }

    @RequestMapping(value = "/getUser/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        List<User> userList = new ArrayList<>();
        val user = userInfoManager.getUserById(id);
        userList.add(userInfoConverter.convert(user));
        return ResponseEntity.ok(userList);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserController.class);
    }
}

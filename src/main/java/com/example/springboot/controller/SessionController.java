package com.example.springboot.controller;

import com.example.springboot.manager.UserInfoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    UserInfoManager userInfoManager;

    @Autowired
    public SessionController(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "username")String username,@RequestParam("password")String password){
        userInfoManager.login(username,password);
        return "success";
    }
}

package com.example.springboot;

import com.example.springboot.converter.c2s.UserInfoConverter;
import com.example.springboot.manager.UserInfoManager;
import com.example.springboot.model.service.User;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/getUser/{id}")
    public User getUserById(@PathVariable("id")long id){
        com.example.springboot.model.common.User user = userInfoManager.getUserById(id);
        log.debug("根据ID获取User数据");
        return userInfoConverter.convert(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserController.class);
    }
}

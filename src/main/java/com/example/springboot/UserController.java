package com.example.springboot;

import com.example.springboot.converter.c2s.UserInfoConverter;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.manager.UserInfoManager;
import com.example.springboot.model.service.User;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


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
    public ResponseEntity<?> getUserById(@PathVariable("id") @NotNull Long id) {
        log.info("----------------------------");
        List<User> userList = new ArrayList<>();
        if (id == null || id < 0L) {
            throw new InvalidateParamException(String.format("INVALIDATE_PARAM %d", id));
        } else {
            val user = userInfoManager.getUserById(id);
            userList.add(userInfoConverter.convert(user));
        }
        return ResponseEntity.ok(userList);
    }
}

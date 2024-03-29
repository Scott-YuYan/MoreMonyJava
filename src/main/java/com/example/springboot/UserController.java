package com.example.springboot;

import com.example.springboot.converter.c2s.UserInfoConverter;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.manager.UserInfoManager;
import com.example.springboot.model.service.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class UserController {
    
    private final UserInfoManager userInfoManager;

    @Qualifier("c2s")
    private final UserInfoConverter userInfoConverter;

    private static final int OK = 200;
    private static final int NOT_FOUND = 404;
    private static final int BAD_REQUEST = 400;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoConverter userInfoConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoConverter = userInfoConverter;
    }

    @ApiOperation(value = "get user by id API", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "success get user by id"),
            @ApiResponse(code = BAD_REQUEST, message = "bad request"),
            @ApiResponse(code = NOT_FOUND, message = "user not found")
    })
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

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(
            @RequestBody com.example.springboot.model.common.User userService) {
        log.info("----------user register");
        val user = userInfoManager.registry(userService.getName(), userService.getPassword());
        return ResponseEntity.ok(user);
    }
}

package com.example.springboot.manager;

import com.example.springboot.config.Constant;
import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.model.common.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserInfoManagerImpl implements UserInfoManager {

    private UserInfoDao userInfoDao;
    private UserInfoConverter userInfoConverter;


    @Autowired
    public UserInfoManagerImpl(UserInfoDao userInfoDao, UserInfoConverter userInfoConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoConverter = userInfoConverter;
    }

    @Override
    public User getUserById(Long id) {
        if (id == null || id < 0) {
            throw new InvalidateParamException("参数不合法");
        }
        return Optional.ofNullable(userInfoDao.getUserById(id))
                .map(userInfoConverter::convert)
                .orElseThrow(
                        () -> (new UserNotFoundException(String.format("用户编号为%d的用户没有找到！", id)
                        )));
    }

    @Override
    public User getUserByUserName(String username) {
        if (username.isEmpty()) {
            throw new InvalidateParamException("用户名不能为空");
        }
        val user = Optional.ofNullable(userInfoDao.getUserByUserName(username))
                .orElseThrow(() -> (
                        new UserNotFoundException(String.format("%s 用户没有找到", username))));
        return userInfoConverter.convert(user);
    }

    @Override
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        subject.login(usernamePasswordToken);
        return "success";
    }

    @Override
    public User registry(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new InvalidateParamException("用户名,密码不能为空");
        }

        val user = userInfoDao.getUserByUserName(username);
        Optional.ofNullable(user)
                .ifPresent(
                        user1 -> {
                            throw new InvalidateParamException(String.format("%s-用户名已经被使用", username));
                        }
                );

        String salt = UUID.randomUUID().toString();
        //加密算法采用Sha256
        String encryptedPassword = new Sha256Hash(password, salt, Constant.HASH_ITERATION_TIMES).toBase64();
        val userInfo = com.example.springboot.model.persistence.User.builder()
                .name(username)
                .password(encryptedPassword)
                .salt(salt)
                .createTime(LocalDate.now(ZoneId.of("UTC+8")))
                .build();

        userInfoDao.createNewUser(userInfo);
        return userInfoConverter.convert(userInfo);
    }
}

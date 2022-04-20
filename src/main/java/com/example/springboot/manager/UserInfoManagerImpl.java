package com.example.springboot.manager;

import java.util.Optional;
import java.util.UUID;

import com.example.springboot.config.Constant;
import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.external.AddressClientService;
import com.example.springboot.model.common.User;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;


@Component
public class UserInfoManagerImpl implements UserInfoManager {

    private final UserInfoDao userInfoDao;
    private final UserInfoConverter userInfoConverterP2C;
    private final com.example.springboot.converter.c2s.UserInfoConverter userInfoConverterC2S;


    @Autowired
    public UserInfoManagerImpl(UserInfoDao userInfoDao,
                               UserInfoConverter userInfoConverterP2C,
                               com.example.springboot.converter.c2s.UserInfoConverter userInfoConverterC2S) {
        this.userInfoDao = userInfoDao;
        this.userInfoConverterP2C = userInfoConverterP2C;
        this.userInfoConverterC2S = userInfoConverterC2S;
    }

    @Override
    public User getUserById(Long id) {
        if (id == null || id < 0) {
            throw new InvalidateParamException("参数不合法");
        }
        return Optional.ofNullable(userInfoDao.getUserById(id))
                .map(userInfoConverterP2C::convert)
                .orElseThrow(
                        () -> (new UserNotFoundException(String.format("用户编号为%d的用户没有找到！", id)
                        )));
    }

    @Override
    public User getUserByUserName(String username) {
        if (username.isEmpty()) {
            throw new InvalidateParamException("用户名不能为空");
        }
        // 类型推断
        val user = Optional.ofNullable(userInfoDao.getUserByUserName(username))
                .orElseThrow(() -> (
                        new UserNotFoundException(String.format("%s 用户没有找到", username))));
//        反转的话，用reverse().convert()
//        userInfoConverterP2C.reverse().convert(user);
        return userInfoConverterP2C.convert(user);

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
            throw new InvalidateParamException("username,password cannot be null");
        }

        val user = userInfoDao.getUserByUserName(username);
        Optional.ofNullable(user)
                .ifPresent(
                        user1 -> {
                            throw new InvalidateParamException(
                                    String.format("%s-用户名已经被使用", username));
                        }
                );

        String salt = UUID.randomUUID().toString();
        //加密算法采用Sha256
        String encryptedPassword = new Sha256Hash(password, salt,
                Constant.HASH_ITERATION_TIMES).toBase64();
        val userInfo = com.example.springboot.model.persistence.User.builder()
                .name(username)
                .createTime(LocalDate.now(ZoneId.of("UTC+8")))
                .build();

        int userId = userInfoDao.createNewUser(userInfo);
        userInfo.setId((long) userId);
        return userInfoConverterP2C.convert(userInfo);
    }
}

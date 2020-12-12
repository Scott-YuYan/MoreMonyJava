package com.example.springboot.manager;

import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.model.common.User;

import java.util.Optional;

import lombok.val;
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
}

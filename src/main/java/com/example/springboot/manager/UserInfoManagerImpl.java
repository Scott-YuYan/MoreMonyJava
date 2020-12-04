package com.example.springboot.manager;

import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.model.common.User;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public User getUserById(long id) {
        if (id < 0){
            throw new InvalidateParamException(String.format("输入编号错误%d",id));
        }
        val user = Optional.ofNullable(userInfoDao.getUserById(id))
                .orElseThrow(()->(new UserNotFoundException(String.format("用户编号为%d的用户没有找到！",id))));
        return userInfoConverter.convert(user);
    }
}

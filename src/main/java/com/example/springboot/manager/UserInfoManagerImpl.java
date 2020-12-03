package com.example.springboot.manager;

import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.model.common.User;
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
    public User getUserById(long id) {
        val user = userInfoDao.getUserById(id);
        return userInfoConverter.convert(user);
    }
}

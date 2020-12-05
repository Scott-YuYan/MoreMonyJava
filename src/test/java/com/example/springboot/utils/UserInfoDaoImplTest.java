package com.example.springboot.utils;

import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.model.persistence.User;

public class UserInfoDaoImplTest implements UserInfoDao {
    @Override
    public User getUserById(Long id) {
        if (id > 0){
            return User.builder()
                    .name("wcadmin")
                    .password("wcadmin")
                    .gender("meal")
                    .id(1L)
                    .build();
        }else {
            throw new InvalidateParamException(String.format("用户ID%d错误",id));
        }
    }
}

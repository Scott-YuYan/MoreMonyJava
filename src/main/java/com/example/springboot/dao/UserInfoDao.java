package com.example.springboot.dao;

import com.example.springboot.model.persistence.User;

public interface UserInfoDao {

    public User getUserById(Long id);
}

package com.example.springboot.dao;

import com.example.springboot.model.persistence.User;

public interface UserInfoDao {

    User getUserById(Long id);
}

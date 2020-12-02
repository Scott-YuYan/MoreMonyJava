package com.example.springboot.manager;

import com.example.springboot.model.common.User;

public interface UserInfoManager {

    User getUserById(long id);
}

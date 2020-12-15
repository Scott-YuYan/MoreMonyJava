package com.example.springboot.manager;

import com.example.springboot.model.common.User;

public interface UserInfoManager {

    User getUserById(Long id);

    /**
     * log bt username and password
     * @param username username
     * @param password username
     */
    void login(String username,String password);
}

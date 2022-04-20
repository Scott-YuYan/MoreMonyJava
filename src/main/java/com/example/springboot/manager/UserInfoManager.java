package com.example.springboot.manager;

import com.example.springboot.model.common.User;

public interface UserInfoManager{

    User getUserById(Long id);

    /**
     * get user by username.
     *
     * @param username username
     * @return common user
     */
    User getUserByUserName(String username);

    /**
     * log by username and password.
     *
     * @param username username
     * @param password username
     */
    String login(String username, String password);

    /**
     * registry by username and password
     *
     * @param username username
     * @param password password
     */
    User registry(String username, String password);

}

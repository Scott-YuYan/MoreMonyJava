package com.example.springboot.converter.c2s;

import com.example.springboot.model.common.User;
import com.google.common.base.Converter;

public class UserInfoConverter extends Converter<User, com.example.springboot.model.service.User> {
    @Override
    protected com.example.springboot.model.service.User doForward(User user) {
        return com.example.springboot.model.service.User.builder()
                .serId(user.getSerId())
                .gender(user.getGender())
                .name(user.getName())
                .build();
    }

    @Override
    protected User doBackward(com.example.springboot.model.service.User user) {
        return User.builder()
                .serId(user.getSerId())
                .name(user.getName())
                .gender(user.gender)
                .build();
    }
}

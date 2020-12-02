package com.example.springboot.converter.c2s;

import com.example.springboot.model.common.User;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

@Component("c2s")
public class UserInfoConverter extends Converter<User, com.example.springboot.model.service.User> {
    @Override
    protected com.example.springboot.model.service.User doForward(User user) {
        return com.example.springboot.model.service.User.builder()
                .id(user.getId())
                .gender(user.getGender())
                .name(user.getName())
                .build();
    }

    @Override
    protected User doBackward(com.example.springboot.model.service.User user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .gender(user.gender)
                .build();
    }
}

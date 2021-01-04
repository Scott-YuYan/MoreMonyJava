package com.example.springboot.converter.p2c;

import com.example.springboot.model.persistence.User;
import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component("p2c")
@EqualsAndHashCode(callSuper = true)
public class UserInfoConverter extends Converter<User, com.example.springboot.model.common.User> {
    @Override
    protected com.example.springboot.model.common.User doForward(User user) {
        return com.example.springboot.model.common.User.builder()
                .id(user.getId())
                .gender(user.gender)
                .name(user.getName())
                .password(user.getPassword())
                .salt(user.getSalt())
                .build();
    }

    @Override
    protected User doBackward(com.example.springboot.model.common.User user) {
        return User.builder()
                .id(user.getId())
                .gender(user.getGender())
                .name(user.getName())
                .password(user.getPassword())
                .salt(user.getSalt())
                .build();
    }
}

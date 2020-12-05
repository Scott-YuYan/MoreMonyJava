package com.example.springboot.converter.c2s;


import com.example.springboot.model.common.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserInfoConverterC2STest {

    @InjectMocks
    private UserInfoConverter userInfoConverter;

    @Test
    void doForwardTest(){
        long id = 1L;
        String name = "wcadmin";
        String password = "password";
        String gender = "meal";
        val user = User.builder()
                .id(id)
                .name(name)
                .password(password)
                .gender(gender)
                .build();
        val result = userInfoConverter.doForward(user);
        org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id",id)
                .hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrPropertyWithValue("gender",gender);
    }

    @Test
    void backForwardTest(){
        long id = 1L;
        String name = "wcadmin";
        String gender = "meal";
        val user = com.example.springboot.model.service.User.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .build();
        val result = userInfoConverter.doBackward(user);
        org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id",id)
                .hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrPropertyWithValue("gender",gender);
    }
}

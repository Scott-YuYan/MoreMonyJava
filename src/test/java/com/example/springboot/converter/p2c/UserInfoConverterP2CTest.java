package com.example.springboot.converter.p2c;

import com.example.springboot.model.persistence.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class UserInfoConverterP2CTest {

    @InjectMocks
    private UserInfoConverter userInfoConverter;

    @Test
     void doForwardTest(){
         long id = 1L;
         String name = "wcadmin";
         String password = "password";
         String gender = "meal";
         LocalDate createTime = LocalDate.now();
         LocalDate modifyTime = LocalDate.now();
         val user = User.builder()
                 .id(id)
                 .name(name)
                 .password(password)
                 .gender(gender)
                 .createTime(createTime)
                 .modifyTime(modifyTime)
                 .build();
         val result = userInfoConverter.doForward(user);
         org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                 .hasFieldOrPropertyWithValue("id",id)
                 .hasFieldOrPropertyWithValue("name",name)
                 .hasFieldOrPropertyWithValue("password",password)
                 .hasFieldOrPropertyWithValue("gender",gender);
     }

     @Test
     void backForwardTest(){
         long id = 1L;
         String name = "wcadmin";
         String password = "password";
         String gender = "meal";
         val user = com.example.springboot.model.common.User.builder()
                 .id(id)
                 .name(name)
                 .password(password)
                 .gender(gender)
                 .build();
         val result = userInfoConverter.doBackward(user);
         org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                 .hasFieldOrPropertyWithValue("id",id)
                 .hasFieldOrPropertyWithValue("name",name)
                 .hasFieldOrPropertyWithValue("password",password)
                 .hasFieldOrPropertyWithValue("gender",gender);
     }
}

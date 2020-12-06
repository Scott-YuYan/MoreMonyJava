package com.example.springboot.controller;

import com.example.springboot.UserController;
import com.example.springboot.converter.c2s.UserInfoConverter;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.manager.UserInfoManager;
import com.example.springboot.model.common.User;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;

    @Mock
    UserInfoConverter userInfoConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGeyUserByIdWithValidateParam() throws Exception {
        //range
        long id = 1L;
        String name = "wcadmin";
        String password = "wcadmin";
        String gender = "meal";
        val userCommon = User.builder()
                .id(id)
                .name(name)
                .password(password)
                .gender(gender)
                .build();
        Mockito.doReturn(userCommon).when(userInfoManager).getUserById(Mockito.anyLong());

        val userService = com.example.springboot.model.service.User.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .build();
        Mockito.doReturn(userService).when(userInfoConverter).convert(Mockito.any(User.class));

        //act && assert
        mockMvc.perform(MockMvcRequestBuilders.get("/getUser/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"wcadmin\",\"gender\":\"meal\"}]"));
        Mockito.verify(userInfoManager).getUserById(Mockito.eq(id));
        Mockito.verify(userInfoConverter).convert(Mockito.any(User.class));
    }

    @Test
    void testGeyUserByIdWithInValidateParam() throws Exception {
        //range
        long id = -1L;
        Mockito.doThrow(InvalidateParamException.class).when(userInfoManager).getUserById(id);
        //act && assert
        Assertions.assertThrows(InvalidateParamException.class,()-> userController.getUserById(id));

        Mockito.verify(userInfoManager).getUserById(Mockito.eq(id));
    }

}

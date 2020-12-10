package com.example.springboot.controller;

import com.example.springboot.UserController;
import com.example.springboot.converter.c2s.UserInfoConverter;
import com.example.springboot.exception.GlobalExceptionHandler;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.manager.UserInfoManager;
import com.example.springboot.model.common.User;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
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
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @AfterEach
    void setBack(){
        Mockito.reset(userInfoManager);
        Mockito.reset(userInfoConverter);
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
    void testGetUserByIdWithInValidateParam() throws Exception {
        //range
        long id = -100L;

       Mockito.doThrow(new InvalidateParamException(String.format("INVALIDATE_PARAM %d",id))).when(userInfoManager).getUserById(id);
        //act && assert
        mockMvc.perform(MockMvcRequestBuilders.get("/getUser/"+id))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":400,\"code\":\"INVALIDATE_PARAM\",\"errorType\":\"Client\",\"message\":\"INVALIDATE_PARAM -100\"}"));

        Mockito.verify(userInfoManager,Mockito.never()).getUserById(Mockito.eq(id));
    }

    @Test
    void testGetUserByIdWithUserNotFound() throws Exception {
        //range
        long id = 100L;
        Mockito.doThrow(new UserNotFoundException(String.format("USER NOT FOUND BY %d",id))).when(userInfoManager).getUserById(id);
        //act && assert
        mockMvc.perform(MockMvcRequestBuilders.get("/getUser/"+id))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":404,\"code\":\"USER_NOT_FOUND\",\"errorType\":\"Client\",\"message\":\"USER NOT FOUND BY 100\"}"));

        Mockito.verify(userInfoManager).getUserById(Mockito.eq(id));
    }

}

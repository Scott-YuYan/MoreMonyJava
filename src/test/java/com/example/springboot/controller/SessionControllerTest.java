package com.example.springboot.controller;

import com.example.springboot.UserController;
import com.example.springboot.exception.GlobalExceptionHandler;
import com.example.springboot.manager.UserInfoManager;

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
public class SessionControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserInfoManager userInfoManager;

    @InjectMocks
    SessionController sessionController;

    @BeforeEach
    void setBefore(){
        mockMvc = MockMvcBuilders.standaloneSetup(sessionController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @AfterEach
    void setBack(){
        Mockito.reset(userInfoManager);
    }


    @Test
    void testLoginWithValidateParam() throws Exception {

        Mockito.doReturn("success").when(userInfoManager).login(Mockito.anyString(),Mockito.anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","zhangsan")
                .param("password","zhangsan"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("success"));
    }

}

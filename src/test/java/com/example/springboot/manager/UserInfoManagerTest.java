package com.example.springboot.manager;

import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.model.persistence.User;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import sun.rmi.server.InactiveGroupException;

import java.time.LocalDate;
import java.time.ZoneId;


@Slf4j
public class UserInfoManagerTest {
    @Mock
    private UserInfoDao userInfoDao;

    private UserInfoManager userInfoManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDao, new UserInfoConverter());
    }

    @Test
    void testGetUserByIdWithValidateIdByMockito() {
        //ranger
        long id = 1L;
        String name = "wcadmin";
        String password = "wcadmin";
        String gender = "meal";
        val user = User.builder()
                .id(id)
                .name(name)
                .password(password)
                .gender(gender)
                .build();
        Mockito.doReturn(user).when(userInfoDao).getUserById(id);
        //action
        val result = userInfoManager.getUserById(id);
        //assert
        org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("gender", gender);
        Mockito.verify(userInfoDao).getUserById(Mockito.eq(id));
    }

    @Test
    void testGetUserByIdWithValidateId() {
        long id = -1L;
        Mockito.doReturn(null).when(userInfoDao).getUserById(id);
        Assertions.assertThrows(InvalidateParamException.class, () -> userInfoManager.getUserById(id));
        Mockito.verify(userInfoDao, Mockito.never()).getUserById(Mockito.eq(id));
    }

    @Test
    void testRegisterWithNullParam() {
        String username = "";
        String password = "";

        Assertions.assertThrows(InvalidateParamException.class,
                () -> userInfoManager.registry(username, password));

        val userInfo = com.example.springboot.model.persistence.User.builder()
                .name(username)
                .password(password)
                .build();

        Mockito.verify(userInfoDao, Mockito.never()).createNewUser(userInfo);
    }

    @Test
    void testRegisterWithExistUsername() {
        String username = "admin";
        String password = "password";
        Assertions.assertThrows(InactiveGroupException.class,
                () -> userInfoManager.registry(username, password));
    }

}

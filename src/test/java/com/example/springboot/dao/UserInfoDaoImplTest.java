package com.example.springboot.dao;

import com.example.springboot.model.persistence.User;
import lombok.val;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
public class UserInfoDaoImplTest {

    @Mock
    private SqlSession sqlSession;

    @InjectMocks
    private UserInfoDaoImpl userInfoDao;

//    @BeforeEach
//    void setUp(){
//        userInfoDao = new UserInfoDaoImpl(sqlSession);
//    }

    @Test
    void testGetUserByIdWithValidateId() {
        //range
        long id = 1L;
        String name = "wcadmin";
        String password = "pasword";
        String gender = "meal";
        LocalDate createTime = LocalDate.now();
        val user = User.builder()
                .id(id)
                .name(name)
                .password(password)
                .gender(gender)
                .createTime(createTime)
                .build();
        //act
        Mockito.doReturn(user).when(sqlSession).selectOne("org.mybatis.example.UserMapper.selectUserById", id);
        //assert
        val result = userInfoDao.getUserById(id);
        org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("gender", gender)
                .hasFieldOrPropertyWithValue("createTime", createTime);

        Assertions.assertEquals(result, user);
        Mockito.verify(sqlSession).selectOne("org.mybatis.example.UserMapper.selectUserById", id);

    }

    @Test
    void testGetUserByIdWithInvalidateId() {
        long id = -1L;
        Mockito.doReturn(null).when(sqlSession).selectOne("org.mybatis.example.UserMapper.selectUserById", id);
        Assertions.assertNull(userInfoDao.getUserById(id));
    }
}

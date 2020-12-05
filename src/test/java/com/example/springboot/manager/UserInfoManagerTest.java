package com.example.springboot.manager;

import com.example.springboot.converter.p2c.UserInfoConverter;
import com.example.springboot.dao.UserInfoDao;
import com.example.springboot.exception.InvalidateParamException;
import com.example.springboot.utils.UserInfoDaoImplTest;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class UserInfoManagerTest {
    private UserInfoDao userInfoDao ;
    private UserInfoConverter userInfoConverter;

    @BeforeEach
    void setUp(){
        userInfoDao = new UserInfoDaoImplTest();
        userInfoConverter = new UserInfoConverter();
    }

    @Test
    void testGetUserByIdWithInvalidateId(){
        //range
        long testId = 1L;
        //action
        var result = userInfoConverter.convert(userInfoDao.getUserById(testId));
        //assert

//        Junit5写法：
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(testId,result.getId());
//        Assertions.assertEquals("wcadmin",result.getName());
//        Assertions.assertEquals("wcadmin",result.getPassword());
//        Assertions.assertEquals("meal",result.getGender());

        //assertj写法
        org.assertj.core.api.Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("name","wcadmin")
                .hasFieldOrPropertyWithValue("password","wcadmin")
                .hasFieldOrPropertyWithValue("gender","meal")
                .hasFieldOrPropertyWithValue("id",testId);
    }


    @Test
    void testGetUserByIdWithValidateId(){
        long testId  = -1L;
        Assertions.assertThrows(InvalidateParamException.class, () -> userInfoConverter.convert(userInfoDao.getUserById(testId)));
    }
}

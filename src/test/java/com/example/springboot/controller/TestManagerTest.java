package com.example.springboot.controller;


import com.example.springboot.manager.TestManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestManagerTest {
    public TestManager testManager;

    @BeforeEach
    public void setUp(){
        if (testManager == null){
            log.info("为testManager设置属性！");
            testManager = new TestManager();
        }
    }

    @AfterEach
    public void setBack(){
        if (testManager!=null){
            log.debug("将testManager充值为空");
            testManager = null;
        }
    }

    @Test
    public void testAddMethod(){
        long testNumber = 100L;
        Assertions.assertEquals(101L,testManager.addMethod(testNumber));
    }
}

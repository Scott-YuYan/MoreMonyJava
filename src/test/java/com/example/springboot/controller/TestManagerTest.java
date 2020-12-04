package com.example.springboot.controller;


import com.example.springboot.manager.TestManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestManagerTest {
    public TestManager testManager = new TestManager();
    @Test
    public void testAddMethod(){
        long testNumber = 100L;
        Assertions.assertEquals(101L,testManager.addMethod(testNumber));
    }
}

package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {


    @Value("${server.context-path}")
    public String port;

    @Value("${server.port}")
    public String defaultPath;

    @RequestMapping("/test")
    public String test() {
        log.debug("默认端口为" + port + "默认路径为" + defaultPath);
        return "默认端口为" + port + "默认路径为" + defaultPath;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/index")
    public String getIndex() {
        return "index html";
    }


}
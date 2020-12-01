package com.example.springboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Value("${server.context-path}")
    public  String port;

    @Value("${server.port}")
    public String defaultPath;

    @RequestMapping("/test")
    public String test(){
        logger.debug("默认端口为"+port+"默认路径为"+defaultPath);
        return "默认端口为"+port+"默认路径为"+defaultPath;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/index")
    public String getIndex(){
        return "index html";
    }


}
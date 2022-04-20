package com.example.springboot.controller;

import com.example.springboot.config.MySelfConfig;
import com.example.springboot.model.Index;
import com.example.springboot.model.service.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/hello/v1")
@Slf4j
public class HelloController {

    private final AtomicLong id = new AtomicLong();

    @Value("${server.servlet.context-path}")
    public String port;

    @Value("${server.port}")
    public String defaultPath;

    @Autowired
    private MySelfConfig mySelfConfig;

    @RequestMapping("/test")
    public String test() {
        log.debug("默认端口为" + port + "默认路径为" + defaultPath);
        return "默认端口为" + port + "默认路径为" + defaultPath + "用户名为"+mySelfConfig.getName()+mySelfConfig.getAge();
    }

    @RequestMapping("/test1")
    public String test1() {
        log.debug("默认端口为" + port + "默认路径为" + defaultPath);
        return "默认端口为" + port + "默认路径为" + defaultPath + "用户名为"+mySelfConfig.getName()+mySelfConfig.getAge();
    }

    /**
     * 1.不要在controller层做过多的逻辑处理
     * 2.参数校验要越早做越好
     * @return
     */
    @RequestMapping(value = "/inde", params = {"index=1"}, method = RequestMethod.GET)
    public Index getIndex1(@RequestParam("index") String index) {
        return new Index(id.getAndIncrement(),index);
    }

    @RequestMapping(value = "/inde", params = {"index=2"}, method = RequestMethod.GET)
    public Index getIndex2(@RequestParam("index") String index) {
        return new Index(id.getAndIncrement(),index);
    }

    @RequestMapping(value = "/findProductInfo", method = RequestMethod.POST,
            consumes = "application/json;charset=utf-8",
            produces = "application/json;charset=utf-8"
    )
    public String findProductInfo(@Validated @RequestBody Product product) {
        return String.format("product info code:%s name:%s",product.getProductCode(),product.getProductName());
    }


}
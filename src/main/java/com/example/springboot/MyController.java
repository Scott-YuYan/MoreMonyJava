package com.example.springboot;

import com.example.springboot.model.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@SpringBootApplication
public class MyController {

    AtomicLong atomicLong = new AtomicLong();

    @Autowired
    @Qualifier("serviceUser")
    User user;

//    @RequestMapping("/getUser/{username}")
//    public User getUser(@PathVariable String username, @RequestParam String gender){
//        return new User(atomicLong.incrementAndGet(),username,gender);
//    }

    public static void main(String[] args) {
        SpringApplication.run(MyController.class, args);
    }
}

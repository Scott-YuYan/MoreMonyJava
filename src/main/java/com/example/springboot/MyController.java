package com.example.springboot;

import com.example.springboot.model.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

@RestController
public class MyController implements HandlerInterceptor{

    User user;

    @Autowired
    public MyController(@Qualifier("serviceUser") User user) {
        this.user = user;
    }

//    @RequestMapping("/getUser/{username}")
//    public User getUser(@PathVariable String username, @RequestParam String gender){
//        return new User(atomicLong.incrementAndGet(),username,gender);
//    }


}

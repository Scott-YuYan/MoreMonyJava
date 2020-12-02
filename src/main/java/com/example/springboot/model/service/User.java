package com.example.springboot.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Controller("serviceUser")
public class User {
    private Long id;

    public String name;

    public String gender;

}

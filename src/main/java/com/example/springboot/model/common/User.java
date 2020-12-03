package com.example.springboot.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Controller("commonUser")
public class User {

    private Long id;

    public String name;

    private String password;

    public String gender;
}

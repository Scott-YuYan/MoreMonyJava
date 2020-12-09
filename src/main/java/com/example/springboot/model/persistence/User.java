package com.example.springboot.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Controller("persistenceUser")
public class User {
    private Long id;

    public String name;

    private String password;

    public String gender;

    private LocalDate createTime;

    private LocalDate modifyTime;

}


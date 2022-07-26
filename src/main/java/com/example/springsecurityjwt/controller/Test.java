package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping("/random")
    public Message randomStuff(){
        return new Message("JWT Hợp lệ mới có thể thấy được message này");
    }
}

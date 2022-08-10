package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.model.response.common.ResponseFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/")
public abstract class BaseController {

    protected final ResponseFactory resFactory;

    public BaseController(ResponseFactory resFactory) {
        this.resFactory = resFactory;
    }
}

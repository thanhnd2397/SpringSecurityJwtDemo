package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.model.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/")
public abstract class BaseController {

    @Autowired
    protected ResponseFactory resFactory;

}

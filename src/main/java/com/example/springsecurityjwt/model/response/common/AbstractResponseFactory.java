package com.example.springsecurityjwt.model.response.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractResponseFactory {
    @Autowired
    protected MessageSource ms;
}


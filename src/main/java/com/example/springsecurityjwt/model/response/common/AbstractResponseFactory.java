package com.example.springsecurityjwt.model.response.common;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractResponseFactory {
    protected final MessageSource ms;

    public AbstractResponseFactory(MessageSource ms) {
        this.ms = ms;
    }
}


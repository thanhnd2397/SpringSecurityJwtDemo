package com.example.springsecurityjwt.common.exeption;

import java.util.ArrayList;
import java.util.List;

public abstract class APIException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String message;
    private transient List<Object> arguments;

    public APIException() {
        super();
        this.message = "error.unknow";
        init();
    }

    public APIException(String message) {
        super();
        this.message = message;
        init();
    }

    public APIException(Throwable e) {
        super();
        this.message = "error.unknow";
        init();
    }

    public APIException(String message, Throwable e) {
        super();
        this.message = message;
        init();
    }

    protected void init() {
        arguments = new ArrayList<>(0);
    }

    public String getMessageDetail() {
        return message;
    }

    protected List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public Object[] getParams() {
        return getArguments().toArray(new Object[getArguments().size()]);
    }
}
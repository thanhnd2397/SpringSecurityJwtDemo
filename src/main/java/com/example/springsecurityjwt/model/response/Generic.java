package com.example.springsecurityjwt.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Generic<T> {
    private int code;
    private String message;
    private T output;

    public Generic(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Generic(int code, String message, T output) {
        this(code, message);
        this.output = output;
    }

    public int getCode() {
        return code;
    }

    public Generic<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Generic<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getOutput() {
        return output;
    }

    public Generic<T> setOutput(T output) {
        this.output = output;
        return this;
    }
}

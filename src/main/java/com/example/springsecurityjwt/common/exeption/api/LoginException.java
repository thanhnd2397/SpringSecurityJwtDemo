package com.example.springsecurityjwt.common.exeption.api;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.helper.utils.TextUtils;
import com.google.common.base.Strings;

import java.io.Serializable;

public class LoginException extends APIException {
    private static final long serialVersionUID = 1L;
    public LoginException() {
        super("Login Fail");
    }

    public LoginException(String msg) {
        super(Strings.isNullOrEmpty(msg) ? "Login Fail" : msg);
    }

    public LoginException(Serializable id, Throwable ex) {
        super("Login Fail", ex);
    }

    @Override
    public String getMessage() {
        return String.format("Login Fail!")
                + (TextUtils.isBlank(super.getMessage()) ? "" : ("\n" + super.getMessage()));
    }
}

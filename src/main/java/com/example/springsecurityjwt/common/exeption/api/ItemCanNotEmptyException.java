package com.example.springsecurityjwt.common.exeption.api;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.helper.utils.TextUtils;
import com.google.common.base.Strings;

import java.io.Serializable;

public class ItemCanNotEmptyException extends APIException {
    private static final long serialVersionUID = 1L;

    public ItemCanNotEmptyException() {
        super("Item can not empty");
    }

    public ItemCanNotEmptyException(String msg) {
        super(Strings.isNullOrEmpty(msg) ? "Item can not empty" : msg);
    }

    public ItemCanNotEmptyException(Serializable id, Throwable ex) {
        super("Item can not empty", ex);
    }

    @Override
    public String getMessage() {
        return String.format("Item is not empty!")
                + (TextUtils.isBlank(super.getMessage()) ? "" : ("\n" + super.getMessage()));
    }
}

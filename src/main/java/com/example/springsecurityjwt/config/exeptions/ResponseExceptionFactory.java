package com.example.springsecurityjwt.config.exeptions;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.common.exeption.api.LoginException;
import com.example.springsecurityjwt.model.response.common.Generic;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResponseExceptionFactory  {

    /**
     * Unknown error.
     *
     * @param ex the ex
     * @param locale the locale
     * @return the generic
     */
    public Generic<Object> unknownError(Exception ex, Locale locale) {
        return new Generic<>(500, "N0001: Access denied");
    }

    public Generic<Object> handleItemCanNotEmptyException(ItemCanNotEmptyException ex, Locale locale) {
        return new Generic<>(getAPIErrorCode(ex), ex.getMessageDetail());
    }

    public Generic<Object> handleLoginException(LoginException ex, Locale locale) {
        return new Generic<>(getAPIErrorCode(ex), ex.getMessageDetail());
    }

    private int getAPIErrorCode(APIException ex) {
        if (ex instanceof ItemCanNotEmptyException) {
            return 401;
        }
        if (ex instanceof LoginException) {
            return 403;
        }
        return 500;
    }

}

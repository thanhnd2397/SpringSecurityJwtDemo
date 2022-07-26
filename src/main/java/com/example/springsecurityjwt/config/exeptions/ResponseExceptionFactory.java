package com.example.springsecurityjwt.config.exeptions;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.model.response.Generic;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResponseExceptionFactory  {

    /**
     * Unknown error.
     *
     * @param ex     the ex
     * @param locale the locale
     * @return the generic
     */
    public Generic<Object> unknownError(Exception ex, Locale locale) {
        return new Generic<>(500, "N0001: Access denied");
    }

    public Generic<Object> handleItemCanNotEmptyException(ItemCanNotEmptyException ex, Locale locale) {
        return new Generic<>(getAPIErrorCode(ex), ex.getMessageDetail());
    }

    private int getAPIErrorCode(APIException ex) {
        if (ex instanceof ItemCanNotEmptyException) {
            return 401;
        }
        return 500;
    }

}

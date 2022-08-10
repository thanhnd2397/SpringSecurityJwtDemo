package com.example.springsecurityjwt.config.exeptions;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.common.exeption.api.LoginException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlingController {
    private static final Level LOG_LEVEL = Level.INFO;

    private static final String LOG_LEVEL_PATTERN = "Request: {0} raised {1}";

    static final Logger log = LogManager.getLogger(ExceptionHandlingController.class);

    @Autowired
    private ResponseExceptionFactory resFactory;

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Object conflict(HttpServletRequest req, Exception ex, Locale locale) {
        log.error(MessageFormat.format(LOG_LEVEL_PATTERN, req.getRequestURL(), ex), ex);
        return resFactory.unknownError(ex, locale);
    }

    @ExceptionHandler(ItemCanNotEmptyException.class)
    @ResponseBody
    public Object handleItemCanNotEmptyException(HttpServletRequest req, ItemCanNotEmptyException ex,
                                                            Locale locale) {
        log.log(LOG_LEVEL, MessageFormat.format(LOG_LEVEL_PATTERN, req.getRequestURL(), ex), ex.getMessage());
        return ResponseEntity.status(getAPIErrorCode(ex)).body(resFactory.handleItemCanNotEmptyException(ex, locale));
    }

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Object handleLoginException(HttpServletRequest req, LoginException ex,
                                                            Locale locale) {
        log.log(LOG_LEVEL, MessageFormat.format(LOG_LEVEL_PATTERN, req.getRequestURL(), ex), ex.getMessage());
        return ResponseEntity.status(getAPIErrorCode(ex)).body(resFactory.handleLoginException(ex, locale));
    }

    private int getAPIErrorCode(APIException ex) {
        if (ex instanceof ItemCanNotEmptyException) {
            return 400;
        }
        if (ex instanceof LoginException) {
            return 401;
        }
        return 500;
    }
}

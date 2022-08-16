package com.example.springsecurityjwt.model.response.common;

import com.example.springsecurityjwt.helper.CodeConst;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResponseFactory extends AbstractResponseFactory {

    public ResponseFactory(MessageSource ms) {
        super(ms);
    }

    /**
     * Ok.
     *
     * @return the generic
     */
    public Generic<Object> ok(String message) {
        return new Generic<Object>(CodeConst.OK, message, null)
                .setOutput("");
    }

    public <T> Generic<T> ok(String messageCode, Locale locale, T output) {
        return new Generic<T>(CodeConst.OK,  ms.getMessage(messageCode, null, locale), output);

    }

    public <T> Generic<T> ok( T output, String message) {
        return new Generic<T>(CodeConst.OK, message, output);
    }

    /**
     * Empty.
     *
     * @return the generic
     */
    public Generic<Object> empty(  String message) {
        return ok(message);
    }

    /**
     * Fail.
     *
     * @param locale      the locale
     * @return the generic
     */
    public Generic<Object> fail(Locale locale, String message) {
        return new Generic<Object>(CodeConst.ERROR, message);
    }

    public Generic<Object> fail(String message) {
        return new Generic<Object>(CodeConst.ERROR, message);
    }

    /**
     * Fail.
     *
     * @param locale the locale
     * @return the generic
     */
    public Generic<Object> fail(Locale locale) {
        return fail(locale, "An error occurred. Please contact the administrator!");
    }

    /**
     * Not found.
     *
     * @param locale the locale
     * @return the generic
     */
    public Generic<Object> notFound(Locale locale) {
        return new Generic<Object>(CodeConst.NOT_FOUND, "Not found");
    }

    /**
     * Ok.
     *
     * @param locale      the locale
     * @return the object
     */
    public Generic<Object> ok(Locale locale, String message) {
        return new Generic<Object>(CodeConst.OK, message);
    }

    /**
     * Ok.
     *
     * @param locale      the locale
     * @param code        the code
     * @return the object
     */
    public Generic<Object> create(Locale locale, int code, String message) {
        return new Generic<Object>(code, message);
    }

    /**
     * Warn.
     *
     * @param message the message
     * @return the generic
     */
    public Generic<Object> warn(String message) {
        return new Generic<Object>(CodeConst.WARN, message);
    }
}

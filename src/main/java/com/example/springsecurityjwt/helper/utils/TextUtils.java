package com.example.springsecurityjwt.helper.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.base.Strings;

/**
 * The Class TextUtils.
 */
public class TextUtils {

    private TextUtils() {

    }

    /**
     * Checks if it is blank.
     *
     * @param text the text
     * @return true, if is blank
     */
    public static boolean isBlank(String text) {
        return Strings.isNullOrEmpty(text);
    }

    /**
     * Checks if is not blank.
     *
     * @param text the text
     * @return true, if is not blank
     */
    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    /**
     * Checks if it is null or empty.
     *
     * @param text the text
     * @return true, if is null or empty
     */
    public static boolean isNullOrEmpty(String text) {
        return isBlank(text);
    }

    /**
     * Checks if it is null object or empty string.
     *
     * @param o the o
     * @return true, if is object null or empty
     */
    public static boolean isObjNullOrEmpty(Object o) {
        return o == null || Strings.isNullOrEmpty(o.toString());
    }

    /**
     * Share an element.
     *
     * @param x the x
     * @param y the y
     * @return true, if successful
     */
    public static boolean shareAnElement(Object[] x, Object[] y) {
        Set<Object> set = new HashSet<>(Arrays.asList(y));
        return Arrays.stream(x).anyMatch(set::contains);
    }

    /**
     * Replace null.
     *
     * @param obj the object
     * @return the string
     */
    public static String repNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * Replace null.
     *
     * @param str the string
     * @return the string
     */
    public static String repNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * Trim.
     *
     * @param str the str
     * @return the string
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * Escape log.
     *
     * @param str the string
     * @return the string
     */
    public static String escapeLog(String str) {
        return escapeLogWith(str, false);
    }

    /**
     * Escape log with.
     *
     * @param str the string
     * @param quote the quote
     * @return the string
     */
    public static String escapeLogWith(String str, boolean quote) {
        String newStr = str == null ? "" : getEscape(str);
        if(quote) {
            return "\"" + newStr + "\"";
        }
        return newStr;
    }

    /**
     * Gets the escape.
     *
     * @param str the string
     * @return the escape
     */
    private static String getEscape(String str) {
        return str.replaceAll("\\\\", "\\\\\\\\")
                .replaceAll(",", "\\\\,")
                .replaceAll("\\{", "\\\\{")
                .replaceAll("\\}", "\\\\}")
                .replaceAll("\\[", "\\\\[")
                .replaceAll("\\]", "\\\\]")
                .replaceAll("\"", "\\\\\"\"");
    }

    /**
     * Escape json.
     *
     * @param input the input
     * @return the string
     */
    public static String escapeJson(String input) {
        return StringEscapeUtils.escapeJson(input);
    }
}

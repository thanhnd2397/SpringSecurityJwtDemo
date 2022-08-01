package com.example.springsecurityjwt.helper;

public class Constants {
    public static final String[] API_AUTHENTICATED_ALLOWS = getApiAuthenticatedAllows();
    public static final int MAX_EXPIRATION_TIME = 12;

    private static String[] getApiAuthenticatedAllows() {
        String path = "/api/v1/";
        return new String[] {
                path + "message",
        };
    }
}

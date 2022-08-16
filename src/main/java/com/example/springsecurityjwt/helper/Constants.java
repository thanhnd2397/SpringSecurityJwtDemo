package com.example.springsecurityjwt.helper;

public class Constants {
    public static final String[] API_AUTHENTICATED_ALLOWS = getApiAuthenticatedAllows();
    public static final int MAX_EXPIRATION_TIME = 12;
    public static final float POINTS_PER_INCH = 72;
    public static final float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;
    public static final float LINE_HORIZONTAL = 205;
    public static final float LINE_VERTICAL = 5;
    public static final float A4_HEIGHT = 297;
    public static final float WIDTH = 340.28f;
    public static final float SCALE = WIDTH / 94;

    private static String[] getApiAuthenticatedAllows() {
        String path = "/api/v1/";
        return new String[] {
                path + "message",
                path + "export"
        };
    }
}

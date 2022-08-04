package com.example.springsecurityjwt.helper;

public class RedisKeyHelper {
    public static String buildApiKey(String apiResource) {
        return "mp_Key:" + apiResource;
    }
}

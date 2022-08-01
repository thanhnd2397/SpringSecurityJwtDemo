package com.example.springsecurityjwt.helper;

public class RedisKeyHelper {
    public static String buildApiKey(String groupName, String apiResource) {
        return "mp_" + groupName + ":" + apiResource;
    }
}

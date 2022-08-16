package com.example.springsecurityjwt.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ValueRequest {
    private String text;
    private MultipartFile image;
    private Long itemId;
}

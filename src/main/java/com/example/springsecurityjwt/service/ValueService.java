package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.entities.Value;
import com.example.springsecurityjwt.service.base.RestService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ValueService extends RestService<Value, Long> {
    String uploadFile(MultipartFile mFile, String fileName) throws IOException;

    File convertToFile(MultipartFile multipartFile, String fileName);
}

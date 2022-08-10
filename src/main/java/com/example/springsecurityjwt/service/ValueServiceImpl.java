package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.entities.Value;
import com.example.springsecurityjwt.service.base.RestServiceImpl;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Primary
@Service
public class ValueServiceImpl extends RestServiceImpl<Value, Integer> implements ValueService{

    private final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/thanh-8f7b4.appspot.com/o/<bucket name>?alt=media&token=49543421-cd2c-4e21-9519-485b337180e1";

    @Override
    public String uploadFile(MultipartFile mFile, String fileName) throws IOException {
        File file = convertToFile(mFile, fileName);
        BlobId blobId = BlobId.of("thanh-8f7b4.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("firebasekey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    @Override
    public File convertToFile(MultipartFile multipartFile, String fileName) {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}

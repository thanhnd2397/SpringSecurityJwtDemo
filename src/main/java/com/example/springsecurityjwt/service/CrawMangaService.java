package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.entities.Chap;

import java.io.IOException;
import java.util.ArrayList;

public interface CrawMangaService {
    ArrayList<Chap> getAllChapInPage(String urls) throws IOException;
}

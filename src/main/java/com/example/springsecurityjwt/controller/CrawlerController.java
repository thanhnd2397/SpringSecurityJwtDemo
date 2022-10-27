package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.service.CrawMangaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class CrawlerController {
    final CrawMangaService service;

    public CrawlerController(CrawMangaService service) {
        this.service = service;
    }

    @GetMapping("/crawler")
    public void crawlerChap() throws IOException {
        service.getAllChapInPage("https://manhuarock.net/truyen/ta-troi-sinh-da-la-nhan-vat-phan-dien/456");
    }

}

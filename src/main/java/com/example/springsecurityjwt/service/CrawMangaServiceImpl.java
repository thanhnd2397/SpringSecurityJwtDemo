package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.entities.Chap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Primary
@Service
public class CrawMangaServiceImpl implements CrawMangaService{

    @Override
    public ArrayList<Chap> getAllChapInPage(String urls) throws IOException {
        ArrayList<Chap> list_chap = new ArrayList<>();
        Document document = Jsoup.connect(urls).userAgent("Mozilla").get();
        Elements elms = document.getElementsByClass("a-h");
        for (org.jsoup.nodes.Element elm : elms) {
            Elements elm_row = elm.getElementsByTag("a");
            for (int j = 0; j < elm_row.size(); j++) {
                String link_chap = elm_row.first().absUrl("href");
                list_chap.add(new Chap(link_chap));
            }
        }
        for (Chap chap: list_chap) {
            if (chap.getUrl().length() > 60 ){
                saveFile(chap);
            }
        }
        return list_chap;
    }

    private ArrayList<String> listImgOnPage(String pageURL) throws IOException, InterruptedException {
        Document document = Jsoup.connect(pageURL).userAgent("Mozilla").get();
        TimeUnit.SECONDS.sleep(5);
        ArrayList<String> list_img = new ArrayList<>();
        Elements elms = document.getElementsByClass("container-chapter-reader");
        for (org.jsoup.nodes.Element e: elms) {
            Elements elm = e.getElementsByTag("img");
            for (org.jsoup.nodes.Element element : elm) {
                String url = element.absUrl("src");
                if (url.equals("")) {
                    continue;
                }
                list_img.add(url);
            }
        }
        return list_img;
    }

    private void saveImg(String src_image, String name, String chap) {
        try {
            URL url = new URL(src_image);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(chap + "\\" + name));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }
    }

    private void saveFile(Chap chap) {
        try {
            ArrayList<String> list_img = listImgOnPage(chap.getUrl());
            for (int i = 0; i < list_img.size(); i++) {
                String name = i + ".jpg";
                saveImg(list_img.get(i), name, chap.getChap_number());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error to save file !", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Dowload sucessfull chap " + chap.getChap_number());
    }


}

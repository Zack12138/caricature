package com.dls.comic.services;

import com.dls.util.HttpsUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AcgRipService {

    public void scan() {
       // String url = "https://dmhy.anoneko.com/";
       //String url = "https://dmhy.anoneko.com/topics/rss/rss.xml?keyword=Lycoris+Recoil";
        String url = "https://192.168.190.254:8006/";
        //String url = "https://www.baidu.com/";
        Document document = null;
        byte[] bytes = HttpsUtil.httpsConnection(url);
        System.out.println(bytes);
    }


    public static void main(String[] args) {
        AcgRipService acgRipService = new AcgRipService();
        acgRipService.scan();
    }

}

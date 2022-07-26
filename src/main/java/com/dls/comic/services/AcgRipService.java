package com.dls.comic.services;

import com.dls.util.HttpsUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

public class AcgRipService {

    public void scan() {
       // String url = "https://dmhy.anoneko.com/";
       String url = "https://dmhy.anoneko.com/topics/rss/rss.xml?keyword=Lycoris+Recoil";
       // String url = "https://192.168.190.254:8006/";
        //String url = "https://www.baidu.com/";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Elements items = document.select("item");
            System.out.println(items.size());
            for (Element ele: items) {
                String title = ele.select("title").text();
                if (matchName(title,"简体")) {
                    continue;
                }
                System.out.println(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name 需要匹配的名称
     * @param match 匹配规则
     * @return 全部匹配成功返回true 若有一项匹配失败则返回false
     */
    private boolean matchName(String name,String... match){
        if(name == null|match == null){
            return false;
        }
        for (String m : match) {
            if (!Pattern.matches(m, name)) {
                return false;
            }
        }
        return true;
    }
}

package com.dls.test;

import com.dls.reptile.QQqunDownload;
import com.dls.util.SysClipboardUtil;

import java.io.IOException;
import java.util.Scanner;

public class TestQQDownload {


    public static void main(String[] args) {
        testUrl();
    }

    private static void testUrl(){
        String url = SysClipboardUtil.getSysClipboardText();
        test(url);
    }


    private static  void testImg(){

    }

    private static void test(String url) {
        QQqunDownload down = new QQqunDownload();

        try {
            down.download(url,"E:/VOL/F/迅雷下载/和谐物/图片/2204","123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

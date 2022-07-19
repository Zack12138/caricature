package com.dls.test;

import com.dls.util.QRCodeUtil;
import com.dls.util.SysClipboardUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Test {
    public static void main(String[] args) throws Exception {
        Image img = SysClipboardUtil.getImageFromClipboard();
        if(img == null){
            System.out.println("剪切板内容非图片 请核实后再试.");
            return;
        }
        String context = QRCodeUtil.parseQRCode((BufferedImage) img);
        System.out.println(context);
    }
}

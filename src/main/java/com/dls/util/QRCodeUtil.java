package com.dls.util;

import com.dls.constant.ReptileException;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/11/8 0008.
 * 二维码、条形码工具类
 */
public class QRCodeUtil {

    /**
     * CODE_WIDTH：二维码宽度，单位像素
     * CODE_HEIGHT：二维码高度，单位像素
     * FRONT_COLOR：二维码前景色，0x000000 表示黑色
     * BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
     * 演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
     */
    private static final int CODE_WIDTH = 400;
    private static final int CODE_HEIGHT = 400;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    /**
     * 生成二维码 并 保存为图片：write(RenderedImage im,String formatName,File output)
     *
     * @param codeContent        :二维码参数内容，如果是一个网页地址，如 https://www.baidu.com/ 则 微信扫一扫会直接进入此地址
     *                           如果是一些参数，如 1541656080837，则微信扫一扫会直接回显这些参数值
     * @param codeImgFileSaveDir :二维码图片保存的目录,如 D:/codes
     * @param fileName           :二维码图片文件名称，带格式,如 123.png
     */
    public static void createCodeToFile(String codeContent, File codeImgFileSaveDir, String fileName) {
        try {
            /* 参数检验*/
            if (StringUtil.isBlank(codeContent)) {
                System.out.println("二维码内容为空，不进行操作...");
                return;
            }
            codeContent = codeContent.trim();

            if (codeImgFileSaveDir == null || codeImgFileSaveDir.isFile()) {
                codeImgFileSaveDir = FileSystemView.getFileSystemView().getHomeDirectory();
                System.out.println("二维码图片存在目录为空，默认放在桌面...");
            }
            if (!codeImgFileSaveDir.exists()) {
                codeImgFileSaveDir.mkdirs();
                System.out.println("二维码图片存在目录不存在，开始创建...");
            }
            if (fileName == null || "".equals(fileName)) {
                fileName = System.currentTimeMillis() + ".png";
                System.out.println("二维码图片文件名为空，随机生成 png 格式图片...");
            }

            /*com.google.zxing.EncodeHintType：编码提示类型,枚举类型
             * EncodeHintType.CHARACTER_SET：设置字符编码类型
             * EncodeHintType.ERROR_CORRECTION：设置误差校正
             *      ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
             *      不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
             * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
             * */
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 1);

            /*
             * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
             *      encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
             *      contents:条形码/二维码内容
             *      format：编码类型，如 条形码，二维码 等
             *      width：码的宽度
             *      height：码的高度
             *      hints：码内容的编码类型
             * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
             * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
             */
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);

            /*java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
             * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
             * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
             *      x：像素位置的横坐标，即列
             *      y：像素位置的纵坐标，即行
             *      rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
             */
            BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
            for (int x = 0; x < CODE_WIDTH; x++) {
                for (int y = 0; y < CODE_HEIGHT; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
                }
            }

            /*javax.imageio.ImageIO java 扩展的图像IO
             * write(RenderedImage im,String formatName,File output)
             *      im：待写入的图像
             *      formatName：图像写入的格式
             *      output：写入的图像文件，文件不存在时会自动创建
             *
             * 即将保存的二维码图片文件*/
            File codeImgFile = new File(codeImgFileSaveDir, fileName);
            ImageIO.write(bufferedImage, "png", codeImgFile);

            System.out.println("二维码图片生成成功：" + codeImgFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码 并 输出到输出流————通常用于输出到网页上进行显示
     * 输出到网页与输出到磁盘上的文件中，区别在于最后一句 ImageIO.write
     * write(RenderedImage im,String formatName,File output)：写到文件中
     * write(RenderedImage im,String formatName,OutputStream output)：输出到输出流中
     *
     * @param codeContent  ：二维码内容
     * @param outputStream ：输出流，比如 HttpServletResponse 的 getOutputStream
     */
    public static void createCodeToOutputStream(String codeContent, OutputStream outputStream) {
        try {
            /* 参数检验*/
            if (codeContent == null || "".equals(codeContent.trim())) {
                System.out.println("二维码内容为空，不进行操作...");
                return;
            }
            codeContent = codeContent.trim();

            /*
             * com.google.zxing.EncodeHintType：编码提示类型,枚举类型
             * EncodeHintType.CHARACTER_SET：设置字符编码类型
             * EncodeHintType.ERROR_CORRECTION：设置误差校正
             * ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
             *      不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
             * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
             * */
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 1);

            /*
             * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
             *      encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
             *      contents:条形码/二维码内容
             *      format：编码类型，如 条形码，二维码 等
             *      width：码的宽度
             *      height：码的高度
             *      hints：码内容的编码类型
             * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
             * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
             */
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);

            /*
             * java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
             * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
             * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
             *      x：像素位置的横坐标，即列
             *      y：像素位置的纵坐标，即行
             *      rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
             */
            BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
            for (int x = 0; x < CODE_WIDTH; x++) {
                for (int y = 0; y < CODE_HEIGHT; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
                }
            }

            /*
             * 区别就是以一句，输出到输出流中，如果第三个参数是 File，则输出到文件中
             */
            ImageIO.write(bufferedImage, "png", outputStream);

            System.out.println("二维码图片生成到输出流成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 根据本地二维码图片————————解析二维码内容
     * （注：图片必须是二维码图片，但也可以是微信用户二维码名片，上面有名称、头像也是可以的）
     *
     * @param file 本地二维码图片文件,如 E:\\logs\\2.jpg
     * @return
     * @throws Exception
     */
    public static String parseQRCodeByFile(File file) {
        if (file == null || file.isDirectory() || !file.exists()) {
            return null;
        }
        try {
            /*
             * ImageIO 的 BufferedImage read(URL input) 方法用于读取网络图片文件转为内存缓冲图像
             * 同理还有：read(File input)、read(InputStream input)、、read(ImageInputStream stream)
             */
            return parseQRCode(ImageIO.read(file));
        } catch (IOException e) {
            throw new ReptileException("二维码图片路径错误",e);
        } catch (NotFoundException e) {
            throw new ReptileException("图片非二维码图片"+file.getPath(),e);
        }
    }


    /**
     * 根据网络二维码图片————————解析二维码内容
     * （区别仅仅在于 ImageIO.read(url); 这一个重载的方法）
     *
     * @param url 二维码图片网络地址，如 https://res.wx.qq.com/mpres/htmledition/images/mp_qrcode3a7b38.gif
     */
    public static String parseQRCodeByUrl(URL url) {
        if (url == null) {
            return null;
        }
        try {
            /*
             * ImageIO 的 BufferedImage read(URL input) 方法用于读取网络图片文件转为内存缓冲图像
             * 同理还有：read(File input)、read(InputStream input)、、read(ImageInputStream stream)
             *
             * 如果图片网络地址错误，比如不能访问，则 read 抛异常：javax.imageio.IIOException: Can't get input stream from URL!
             */
            return parseQRCode(ImageIO.read(url));
        } catch (IOException e) {
            throw new ReptileException("二维码图片地址错误"+url,e);
        } catch (NotFoundException e) {
            throw new ReptileException("图片非二维码图片"+url,e);
        }
    }

    public static String parseQRCode(BufferedImage bufferedImage) throws NotFoundException {
        if (bufferedImage == null) {
            return null;
        }
        /*
         * com.google.zxing.client.j2se.BufferedImageLuminanceSource：缓冲图像亮度源
         * 将 java.awt.image.BufferedImage 转为 zxing 的 缓冲图像亮度源
         * 关键就是下面这几句：HybridBinarizer 用于读取二维码图像数据，BinaryBitmap 二进制位图
         */
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        /*
         * 如果内容包含中文，则解码的字符集格式应该和编码时一致
         */
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        /*
         * 如果图片不是二维码图片，则 decode 抛异常：com.google.zxing.NotFoundException
         * MultiFormatWriter 的 encode 用于对内容进行编码成 2D 矩阵
         * MultiFormatReader 的 decode 用于读取二进制位图数据
         */
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    public static void main(String[] args) throws MalformedURLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入文件路径");
        String str = sc.nextLine();

        File localFile = new File(str);

//        createCodeToFile("12345Test",localFile.getParentFile(),localFile.getName());


        String localQRCodeContent = parseQRCodeByFile(localFile);
        System.out.println(localFile + " 二维码内容：" + localQRCodeContent);

//        URL url = new URL("https://res.wx.qq.com/mpres/htmledition/images/mp_qrcode3a7b38.gif");
//        String netQRcodeContent = parseQRCodeByUrl(url);
//        System.out.println(url + " 二维码内容：" + netQRcodeContent);
    }
}
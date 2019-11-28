package com.onnoa.springboot.captche.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 验证码工具类
 * @Author: onnoA
 * @Date: 2019/11/28 09:21
 */
@Slf4j
public class VerifyCodeUtil {

    private static final Random random = new Random();
    private static final String[] fontNames = {"宋体", "华文楷体", "黑体", "Georgia", "微软雅黑", "楷体_GB2312"};
    private static int width = 200;
    private static int height = 50;

    public static Map<String, String> getVerifyCode() {
        try {
            String code = "";
            //创建图片缓冲区
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            // 创建绘图对象
            Graphics2D g = bi.createGraphics();
            //设置背景颜色
            g.setBackground(new Color(255, 255, 255));
            g.clearRect(0, 0, width, height);
            StringBuilder sb = new StringBuilder();
            //随机生辰4位验证码
            for (int i = 0; i < 4; i++) {
                //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
                String s = randomChar() + "";
                //添加到StringBuilder里面
                sb.append(s);
                //定义字符的x坐标
                float x = i * 1.0F * width / 4;
                //设置字体，随机
                g.setFont(randomFont());
                //设置颜色，随机
                g.setColor(randomColor());
                g.drawString(s, x, height - 5);
            }
            code = sb.toString();
            // 定义干扰线 数量（3-5条）int num = random.nextInt(max)/(max/2) + min;
            int disturbNum = random.nextInt(7) / 3 + 3;
            // 得到画笔
            Graphics2D graphics = (Graphics2D) bi.getGraphics();
            // 绘画干扰线
            for (int i = 0; i < disturbNum; i++) {
                int x1 = random.nextInt(width);
                int y1 = random.nextInt(height);
                int x2 = random.nextInt(width);
                int y2 = random.nextInt(height);
                graphics.setColor(randomColor());
                graphics.drawLine(x1, y1, x2, y2);
            }
            // 释放图形上下文
            g.dispose();
            // 创建字符流
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            // 写入字符流
            ImageIO.write(bi, "jpg", bs);
            // 转码成字符串
            String jpgEncode = Base64.getEncoder().encodeToString(bs.toByteArray());
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            map.put("base64", jpgEncode);
            // 返回验证码
            return map;
        } catch (IOException e) {
            log.error("生成验证码异常!");
            return null;
        }
    }

    public static String drawImage(ByteArrayOutputStream output, HttpServletResponse response) {
        try {

            //创建图片缓冲区
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            Graphics2D g = bi.createGraphics();

            //设置背景颜色
            g.setBackground(new Color(255, 255, 255));
            g.clearRect(0, 0, width, height);

            StringBuilder stringBuilder = new StringBuilder();
            //这里只画入四个字符
            for (int i = 0; i < 4; i++) {
                //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
                String s = randomChar() + "";
                //添加到StringBuilder里面
                stringBuilder.append(s);
                //定义字符的x坐标
                float x = i * 1.0F * width / 4;
                //设置字体，随机
                g.setFont(randomFont());
                //设置颜色，随机
                g.setColor(randomColor());
                g.drawString(s, x, height - 5);
            }

            // 将图片以流的形式输出
            ServletOutputStream sos = response.getOutputStream();
            // 创建编码对象
            Base64.Encoder base64 = Base64.getEncoder();
            // 创建字符流
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            // 写入字符流
            ImageIO.write(bi, "jpg", bs);
            // 转码成字符串
            String imgsrc = base64.encodeToString(bs.toByteArray());
            //code = stringBuilder.toString();//获取验证码字符串

            //定义干扰线
            //定义干扰线的数量（3-5条）int num = random.nextInt(max)%(max-min+1) + min;
            int num = random.nextInt(5) % 3 + 3;
            Graphics2D graphics = (Graphics2D) bi.getGraphics();
            for (int i = 0; i < num; i++) {
                int x1 = random.nextInt(width);
                int y1 = random.nextInt(height);
                int x2 = random.nextInt(width);
                int y2 = random.nextInt(height);
                graphics.setColor(randomColor());
                graphics.drawLine(x1, y1, x2, y2);
            }
            // 释放图形上下文
            g.dispose();
            try {
                ImageIO.write(bi, "jpg", output);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imgsrc;//为了方便取值，直接返回code，

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //随机字体
    private static Font randomFont() {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = random.nextInt(4);         //随机获取4种字体的样式
        int size = random.nextInt(20) % 6 + 15;    //随机获取字体的大小(10-20之间的值)
        return new Font(fontName, style, size);
    }

    //随机颜色
    private static Color randomColor() {
        int r = random.nextInt(225);
        int g = random.nextInt(225);
        int b = random.nextInt(225);
        return new Color(r, g, b);
    }


    //随机字符
    private static char randomChar() {
        //A-Z,a-z,0-9 验证码值从此字符串中获取
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        // random.nextInt(str.length()) 生成一个随机的int值，该值介于[0,str.length())的区间，也就是0到str.length()之间的随机int值，包含0而不包含str.length()。
        // charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。
        return str.charAt(random.nextInt(str.length()));
    }


}

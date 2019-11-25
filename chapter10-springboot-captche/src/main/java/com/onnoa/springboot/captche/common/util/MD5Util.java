package com.onnoa.springboot.captche.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Description: 密码加密工具类
 * @Author: onnoA
 * @Date: 2019/11/25 13:49
 */
@Slf4j
public class MD5Util {

    public static void main(String[] args) {
        String password = md5Pwd("23456");
        System.out.println("普通MD5:" + password);
        String generate = md5PwdWithSalt("23456");
        System.out.println("加盐MD5:" + generate);
        boolean verify = verify("23456", "29f227f2a270802e8c50f605392c10c2005518889ab7d441");
        System.out.println(verify == true ? "登录验证成功" : "登录验证失败");
    }

    /**
     * 功能描述: 普通MD5
     *
     * @param:
     * @auther: onnoA
     * @date: 2019/11/25 14:03
     */
    public static String md5Pwd(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 功能描述: 加盐并进行Md5加密
     *
     * @param:
     * @auther: onnoA
     * @date: 2019/11/25 14:03
     */
    public static String md5PwdWithSalt(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        log.info("盐值为：" + salt);
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 功能描述: 校验加盐后是否和原密码一致
     *
     * @param:
     * @auther: onnoA
     * @date: 2019/11/25 14:03
     */
    public static boolean verify(String password, String md5Pwd) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5Pwd.charAt(i);
            cs1[i / 3 * 2 + 1] = md5Pwd.charAt(i + 2);
            cs2[i / 3] = md5Pwd.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String str) {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 生成摘要
            byte[] bs = md5.digest(str.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }
}

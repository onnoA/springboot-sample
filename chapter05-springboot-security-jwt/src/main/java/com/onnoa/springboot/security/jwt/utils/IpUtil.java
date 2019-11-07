package com.onnoa.springboot.security.jwt.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 获取ip地址工具类
 * @Author: onnoA
 * @Date: 2019/11/4 16:13
 */
public class IpUtil {

    /**
     * 功能描述: 获取ip地址
     * @param request
     * @auther: onnoA
     * @date: 2019/11/4 16:14
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

}

package com.onnoa.springboot.redis.utils;

/**
 * 功能描述: 项目redis key前缀 统一管理
 * @auther: onnoA
 * @date: 2019/11/12 11:27
 */
public class PrefixConstant {

    /**
     * 项目redis key前缀
     */
    public static final String ONNOA = "onnoa:";

    /**
     * 服务【用户】redis key前缀
     */
	public static final String TBUSER = ONNOA + "tbuser:";


}

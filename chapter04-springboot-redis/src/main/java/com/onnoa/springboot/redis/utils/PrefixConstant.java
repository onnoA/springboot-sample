package com.onnoa.springboot.redis.utils;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/1 18:07
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

    /**
     * 服务【活动】redis key前缀
     *//*
    public static final String ACTIVITY = ONNOA + "activity:";

    *//**
     * 服务【消息】redis key前缀
     *//*
    public static final String MESSAGE = ONNOA + "message:";

    *//**
     * 服务【分时预订】redis key前缀
     *//*
    public static final String TIMESHARE = ONNOA + "timeshare:";*/
}

package com.onnoa.springboot.redis.utils;

/**
 * @Description: redis 基础类 == > 用于定义前缀和过期时间
 * @Author: onnoA
 * @Date: 2019/11/1 16:28
 */
public class BasePrefix {

    private int expiredTime;
    private String prefix;

    public BasePrefix(String prefix, int expiredTime) {
        this.expiredTime = expiredTime;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        // 0 表示永不过期
        this(prefix, 0);
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


}

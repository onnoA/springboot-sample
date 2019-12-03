package com.onnoa.springboot.redis.utils;

/**
 * @Description: redis 前缀常量枚举
 * @Author: onnoA
 * @Date: 2019/11/30 12:56
 */
public enum PrefixConstantEnum {



    USER_SERVICE_KEY("user:service:key:");

    private int expiredTime;
    private String prefix;

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

    PrefixConstantEnum(String prefix) {
        this.prefix = prefix;
    }

    PrefixConstantEnum(int expiredTime, String prefix) {
        this.expiredTime = expiredTime;
        this.prefix = prefix;
    }


}

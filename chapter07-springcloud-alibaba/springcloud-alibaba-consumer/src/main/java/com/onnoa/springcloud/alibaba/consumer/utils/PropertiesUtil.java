package com.onnoa.springcloud.alibaba.consumer.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: 读取配置文件信息
 * @Author: onnoA
 * @Date: 2019/11/11 17:59
 */
public class PropertiesUtil extends PropertyPlaceholderConfigurer {

    private static Map<String, String> propertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException {
        super.processProperties(beanFactory, properties);
        propertiesMap = new HashMap<>();
        for (Object key : properties.keySet()) {
            String keyStr = key.toString();
            String value = properties.getProperty(keyStr);
            properties.put(keyStr,value);
        }
    }

    /**
     * 功能描述: 获取配置值
     * @auther: onnoA
     * @date: 2019/11/11 18:11
     */
    public static String getValue(String key){
        String value = propertiesMap.get(key);
        if(value == null){
            return null;
        }
        return value;
    }

    /**
     * 功能描述: 取出String类型的Property 如果没配置，默认返回空字符串
     * @auther: onnoA
     * @date: 2019/11/11 18:13
     */
    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    /**
     * 功能描述: 取出String类型的Property，如果都為Null則返回Default值.
     * @auther: onnoA
     * @date: 2019/11/11 18:14
     */
    public static String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 功能描述: 取出Integer类型的Property 如果没配置，默认返回0
     * @auther: onnoA
     * @date: 2019/11/11 18:14
     */
    public static Integer getInteger(String key) {
        return getInteger(key, 0);
    }

    /**
     * 功能描述: 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
     * @auther: onnoA
     * @date: 2019/11/11 18:14
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 功能描述: 如果没配置，默认返回0L
     * @auther: onnoA
     * @date: 2019/11/11 18:14
     */
    public static final Long getLong(String key) {
        return getLong(key, 0L);
    }

    public static final Long getLong(String key, Long defaultValue) {
        String value = getValue(key);
        return value != null ? Long.valueOf(value) : defaultValue;
    }

    /**
     * 功能描述: 取出Double类型的Property.如果内容错误则抛出异常. 如果没配置，默认返回0.0d
     * @auther: onnoA
     * @date: 2019/11/11 18:15
     */
    public static Double getDouble(String key) {
        return getDouble(key, 0.0D);
    }

    /**
     * 功能描述: 取出Double类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
     * @auther: onnoA
     * @date: 2019/11/11 18:16
     */
    public static Double getDouble(String key, Double defaultValue) {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }

    /**
     * 功能描述: 取出Boolean类型的Property.如果内容不是true/false则返回false. 如果没配置，默认返回false
     * @auther: onnoA
     * @date: 2019/11/11 18:16
     */
    public static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 功能描述: 取出Boolean类型的Property.如果都為Null則返回Default值,如果内容不为true/false则返回false.
     * @auther: onnoA
     * @date: 2019/11/11 18:16
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

   /* public static Map<String, String> getProperties() {
        return propertiesMap;
    }*/
}

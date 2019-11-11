package com.onnoa.springboot.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: 读取配置文件信息 ==> 待实现
 * @Author: onnoA
 * @Date: 2019/11/11 16:28
 */
public class PlaceholderHolder extends PropertySourcesPlaceholderConfigurer {

    private static Map<String, String> propertiesMap;


}

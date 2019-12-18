package com.onnoa.security.jwt.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description: 加载 XML 配置类
 * @Author: onnoA
 * @Date: 2019/12/17 11:27
 */
@Configuration
@ImportResource(locations = {"classpath:/spring/applicationContext*.xml"})
public class XMLConfiguration {
}

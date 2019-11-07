package com.onnoa.springboot.security.oauth2.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/31 11:22
 */
@SpringBootApplication
@MapperScan("com.onnoa.springboot.security.oauth2.server.mapper")
public class SpringBootSecurityOAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityOAuth2Application.class,args);
    }
}

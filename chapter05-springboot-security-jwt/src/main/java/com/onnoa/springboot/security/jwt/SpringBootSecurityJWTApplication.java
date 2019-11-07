package com.onnoa.springboot.security.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/4 09:35
 */
@SpringBootApplication
@MapperScan("com.onnoa.springboot.security.jwt.mapper")
public class SpringBootSecurityJWTApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityJWTApplication.class, args);
    }
}

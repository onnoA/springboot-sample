package com.onnoa.security.jwt.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 14:55
 */
@SpringBootApplication
@MapperScan("com.onnoa.security.jwt.login.mapper")
public class SecurityJWTLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityJWTLoginApplication.class,args);

    }
}

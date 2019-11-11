package com.onnoa.springboot.security.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Properties;

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

        /*Properties properties = System.getProperties();
        String property = properties.getProperty("spring.profiles.active");
        System.out.println("property:"+property);
        for (Object key : properties.keySet()) {
            System.out.println("key:"+key);
        }

        Map<String, String> env = System.getenv();

        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println("entry:"+entry);
        }

        for (String value : env.values()) {
            System.out.println("value:"+value);
        }*/

    }
}

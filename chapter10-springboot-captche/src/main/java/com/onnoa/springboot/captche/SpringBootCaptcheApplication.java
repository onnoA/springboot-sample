package com.onnoa.springboot.captche;

import com.onnoa.springboot.captche.common.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Description: SpringBoot启动类
 * @Author: onnoA
 * @Date: 2019/11/22 10:38
 */
@SpringBootApplication
public class SpringBootCaptcheApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCaptcheApplication.class, args);
    }


}

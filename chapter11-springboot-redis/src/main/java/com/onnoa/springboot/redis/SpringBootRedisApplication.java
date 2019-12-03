package com.onnoa.springboot.redis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/1 15:07
 */
@SpringBootApplication
public class SpringBootRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class,args);
    }

    /*@Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }*/

}

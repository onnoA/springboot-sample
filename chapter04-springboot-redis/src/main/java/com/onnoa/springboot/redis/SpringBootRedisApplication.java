package com.onnoa.springboot.redis;

import com.onnoa.springboot.redis.utils.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

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

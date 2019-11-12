package com.onnoa.springboot.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/12 17:51
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(){
        return new RedisTemplate();
    }
}

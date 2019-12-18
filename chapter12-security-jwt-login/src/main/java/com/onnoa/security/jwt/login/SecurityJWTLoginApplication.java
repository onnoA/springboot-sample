package com.onnoa.security.jwt.login;

import com.onnoa.utils.utils.RedisUtil;
import com.onnoa.utils.utils.WeChatPushUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }

    @Bean
    public WeChatPushUtils weChatPushUtils(){
        return new WeChatPushUtils();
    }


}

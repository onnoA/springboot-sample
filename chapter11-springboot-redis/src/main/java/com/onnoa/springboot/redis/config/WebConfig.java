package com.onnoa.springboot.redis.config;

import com.onnoa.springboot.redis.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/2 18:13
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 将拦截器注册到spring
    @Bean
    MyHandlerInterceptor myHandlerInterceptor() {
        return new MyHandlerInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求
    }

}

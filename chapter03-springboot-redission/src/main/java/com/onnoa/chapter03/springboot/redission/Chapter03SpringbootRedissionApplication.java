package com.onnoa.chapter03.springboot.redission;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chapter03SpringbootRedissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter03SpringbootRedissionApplication.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return (Redisson)Redisson.create(config);
    }


}

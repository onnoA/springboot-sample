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

    /**
     * 单机
     * @return
     */
    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }

    /**
     * 集群
     * @return
     */
   /* @Bean
    public Redission redission(){
        Config config = new Config();
        config.useClusterServers()

    }*/


}

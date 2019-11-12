package com.onnoa.springboot.redis.controller;

import com.onnoa.springboot.redis.lock.RedisDistributedLock;
import com.onnoa.springboot.redis.redislock.RedisLock;
import com.onnoa.springboot.redis.utils.RedisUtil;
import com.onnoa.springboot.redis.utils.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/12 10:56
 */
@RestController
public class TestController {

    /*@Autowired
    private RedisTemplate<Object, Object> redisTemplate;*/

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${server.port}")
    private String port;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/index")
    public String index() {
        return "hello nginx" + port;
    }


    @RedisLock
    @RequestMapping(value = "redis_stock")
    public synchronized String redisTest() {
        String valueStr = UUID.randomUUID().toString();
        Integer realValue = 0;
        try {
            Boolean result = redisTemplate.opsForValue().setIfAbsent("test1", valueStr, 30000L, TimeUnit.SECONDS);
            if (!result) {
                System.out.println("抢购失败");
                return "抢购失败";
            }
            Integer stock = (Integer) redisTemplate.opsForValue().get("stock");

            if(stock <= 0){
                System.out.println("抢购失败");
                return "抢购失败";
            }
            realValue =stock - 1;
            redisTemplate.opsForValue().set("stock", realValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String value = (String) redisTemplate.opsForValue().get("test1");
            if (valueStr.equals(value)) {
                // 释放锁
                redisTemplate.delete("test1");
            }
        }
        System.out.println("抢购成功！剩余库存为:" + realValue);
        return "抢购成功！剩余库存为:" + realValue;
    }
}

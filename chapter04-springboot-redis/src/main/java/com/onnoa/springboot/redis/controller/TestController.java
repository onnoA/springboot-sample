package com.onnoa.springboot.redis.controller;

import com.onnoa.springboot.redis.redislock.RedisLock;
import com.onnoa.springboot.redis.utils.PrefixConstant;
import com.onnoa.springboot.redis.utils.RedisUtil;
import com.onnoa.springboot.redis.utils.UserKey;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/redis_set")
    public boolean set() {
        UserKey userKey = new UserKey(PrefixConstant.TBUSER);
        return redisUtil.set(userKey, "stock", String.valueOf(50));
    }

    @RequestMapping("/redis_lock")
    public String index() {
        Integer realStock = 0;
        try {
            boolean result = redisUtil.lock("test", 30000L, 1, 1000L);
            if (!result) {
                System.out.println(redisUtil.get("test"));

                log.info(String.format("获取锁失败,锁对象为:%s", redisUtil.get("test")));
                return "抢购失败，请重试.....";
            }
            UserKey userKey = new UserKey(PrefixConstant.TBUSER);
            Integer stock = new Integer((String) redisUtil.get(userKey, "stock"));
            if (stock > 0) {
                realStock = stock - 1;
                System.out.println("抢购成功......剩余库存" + realStock);
                redisUtil.set(userKey, "stock", String.valueOf(realStock));
            } else {
                return "库存不足，抢购失败....";
            }
        } finally {
            // 释放锁
            redisUtil.releaseLock("test");
        }
        return "抢购成功，剩余库存" + realStock;
    }

}

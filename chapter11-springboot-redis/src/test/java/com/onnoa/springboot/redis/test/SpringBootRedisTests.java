package com.onnoa.springboot.redis.test;

import com.onnoa.springboot.redis.entity.TbUser;
import com.onnoa.springboot.redis.utils.KeyPrefix;
import com.onnoa.springboot.redis.utils.RedisUtil;
import com.onnoa.springboot.redis.utils.UserKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/1 16:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisTests {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void redisTest() {
        UserKey userKey = new UserKey("redis", 3000);
        boolean result = redisUtil.set(userKey, "test", "123456");
        System.out.println("是否成功:" + result);
    }


}

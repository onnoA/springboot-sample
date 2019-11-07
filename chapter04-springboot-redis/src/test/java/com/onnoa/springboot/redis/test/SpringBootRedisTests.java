package com.onnoa.springboot.redis.test;

import com.onnoa.springboot.redis.constant.TbUserRedisKey;
import com.onnoa.springboot.redis.entity.TbUser;
import com.onnoa.springboot.redis.utils.BasePrefix;
import com.onnoa.springboot.redis.utils.RedisUtil;
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
        TbUser tbUser = new TbUser();
        tbUser.setPhone("123456");
        tbUser.setPhone("13422129567");
        tbUser.setId(1l);
        tbUser.setCreated(new Date());
        //BasePrefix basePrefix = new BasePrefix("test:", 0);

        redisUtil.set(new TbUserRedisKey(""),"tbUser",tbUser);
       /* boolean boo = redisUtil.set(basePrefix, "tbUser", tbUser);
        System.out.println(boo);*/
        //redisUtil.del(basePrefix,"test");
    }

    @Test
    public void getByKey() {
        BasePrefix basePrefix = new BasePrefix("test");
        TbUser tbUser = (TbUser) redisUtil.get(basePrefix, "test");
        System.out.println(tbUser);
    }
}

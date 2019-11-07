package com.onnoa.chapter03.springboot.redission;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Chapter03SpringbootRedissionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void secKillTest() {
        //设置一个key，aaa商品的库存数量为100
        /*stringRedisTemplate.opsForValue().set("aaa", "100");
        Assert.assertEquals("100", stringRedisTemplate.opsForValue().get("aaa"));*/

    }

}

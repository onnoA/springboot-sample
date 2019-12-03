package com.onnoa.springboot.redis.controller;


import com.onnoa.springboot.redis.utils.PrefixConstant;
import com.onnoa.springboot.redis.utils.PrefixConstantEnum;
import com.onnoa.springboot.redis.utils.RedisUtil;
import com.onnoa.springboot.redis.utils.UserKey;
import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.exception.ExceptionEnums;
import com.onnoa.utils.response.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ApplicationContext context;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "exception")
    public ResultBean exception() {
        throw new BusinessException(ExceptionEnums.UNKONW_EXCEPTION);
    }

    @RequestMapping(value = "/redis_set")
    public ResultBean set() {
        UserKey userKey = new UserKey(PrefixConstant.TBUSER);
        boolean bool = redisUtil.set(userKey, "stock", String.valueOf(50));
        return new ResultBean(bool == true ? 200 : 500, String.valueOf(bool), null);
    }

    @RequestMapping("/redis_lock")
    public String index() {
        Integer realStock = 0;
        try {
            boolean result = redisUtil.lock("test", 30000L, 1, 1000L);
            if (!result) {
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

    @GetMapping(value = "/reflect")
    public void reflect() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class<?> aClass = Class.forName("com.onnoa.springboot.redis.controller.TestController");
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    System.out.println("参数名:" + parameter.getName());
                }
                if (parameters.length == 1 && parameters[0].getType() == String[].class) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("paramType", String[].class.getName());
                    params.put("params", "模拟请求参数");
                    list.add(params);
                    System.out.println(list);

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

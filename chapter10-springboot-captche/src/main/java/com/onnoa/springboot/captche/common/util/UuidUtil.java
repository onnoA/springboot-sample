package com.onnoa.springboot.captche.common.util;

import org.springframework.context.annotation.Configuration;
import java.util.UUID;

/**
 * @Description: UUID 生成工具类
 * @Author: onnoA
 * @Date: 2019/11/25 09:14
 */
public class UuidUtil {

    /**
     * 功能描述: 生成32位的随机UUID
     * @auther: onnoA
     * @date: 2019/11/25 10:13
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 功能描述: 生成19位UUID，最短的UUID
     * @auther: onnoA
     * @date: 2019/11/25 10:13
     */
    public static String getShortUUID(){
        return NumbersUtil.uuid();
    }



}



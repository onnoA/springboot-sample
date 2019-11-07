package com.onnoa.springboot.redis.constant;

import com.onnoa.springboot.redis.utils.BasePrefix;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/1 17:43
 */
public class TbUserRedisConstant extends BasePrefix {

    // 默认超时时间
    private static int DefaultTimeoutSenonds = 1 * 24 * 60 * 60; // 1 天

    public TbUserRedisConstant(String prefix,int expiredTime){
        super(prefix,expiredTime);
    }

    public TbUserRedisConstant(String prefix) {
        super(prefix);
    }


}

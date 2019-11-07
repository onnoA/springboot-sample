package com.onnoa.springboot.redis.constant;

import com.onnoa.springboot.redis.utils.BasePrefix;
import com.onnoa.springboot.redis.utils.PrefixConstant;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/1 17:59
 */
public class TbUserRedisKey extends BasePrefix {

    // 默认超时时间
    private static int DefaultTimeoutSenonds = 1 * 24 * 60 * 60; // 1 天

    public TbUserRedisKey(String prefix, int expiredTime) {
        super(PrefixConstant.TBUSER + prefix, expiredTime);
    }

    // 默认存储时间为一天
    public TbUserRedisKey(String prefix) {
        super(PrefixConstant.TBUSER + prefix, DefaultTimeoutSenonds);
    }

    /*public static TbUserRedisKey verifyCodeWithPhone(int expiredTime) {
        return new TbUserRedisKey(PrefixConstant.TBUSER + "verifyCode:", expiredTime);
    }

    public static TbUserRedisKey dateOfPV(int expiredTime, String date) {
        return new TbUserRedisKey(PrefixConstant.TBUSER + date + ":pv:", expiredTime);
    }

    public static TbUserRedisKey dateOfUV(int expiredTime, String date, Long activityId) {
        return new TbUserRedisKey(PrefixConstant.TBUSER + date + ":uv:activityId_" + activityId + ":", expiredTime);
    }

    public static TbUserRedisKey inDate(int expiredTime, String date) {
        return new TbUserRedisKey(PrefixConstant.TBUSER + date + ":", expiredTime);
    }*/


}

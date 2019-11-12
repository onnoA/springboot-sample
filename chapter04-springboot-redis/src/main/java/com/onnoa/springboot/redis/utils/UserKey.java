package com.onnoa.springboot.redis.utils;

public class UserKey extends BasePrefix {

    public UserKey(String prefix, int expiredTime) {
        super(prefix, expiredTime);
    }

    public static UserKey userIdWithExpired(int expiredTime) {
        return new UserKey(PrefixConstant.TBUSER + "userId:", expiredTime);
    }

    public static UserKey userVerifyCodeWith(int expiredTime) {
        return new UserKey(PrefixConstant.TBUSER + "verifyCode:", expiredTime);
    }
}

package com.onnoa.springboot.redis.utils;

public class UserKey extends BasePrefix {

    public UserKey(String prefix){
        super(prefix);
    }

    public UserKey(String prefix, int expiredTime) {
        super(prefix, expiredTime);
    }

    public static UserKey userIdWithExpired(PrefixConstantEnum prefixEnums) {
        return new UserKey(prefixEnums.getPrefix(), prefixEnums.getExpiredTime());
    }

    public static UserKey userVerifyCodeWith(int expiredTime) {
        return new UserKey(PrefixConstant.TBUSER + "verifyCode:", expiredTime);
    }
}

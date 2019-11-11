package com.onnoa.springboot.security.jwt.common;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

/**
 * @Description: 自定义业务异常
 * @Author: onnoA
 * @Date: 2019/11/8 16:33
 */
public class BusinessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 3901675775254905815L;

    public static final BusinessException COMMON_PARAMS_NOT_NULL = new BusinessException(10000100, "参数{0}不能为空");
    public static final BusinessException COMMON_PARAMS_IS_ILLICIT = new BusinessException(10000100, "{}");


    protected int code;
    protected String msg;

    public BusinessException format(Object... args) {
        return this.newInstance( MessageFormat.format(this.msg, args));
    }

    public BusinessException newInstance(String msg){
        try {
            Class<?> clazz = this.getClass();
            Constructor<?> constructor;
            constructor = clazz.getDeclaredConstructor(new Class[]{int.class, String.class});
            constructor.setAccessible(true);
            BusinessException bus;
            bus = (BusinessException) constructor.newInstance(this.code,msg);
            return bus;
        } catch (Exception e) {
            throw new RuntimeException("创建业务异常新实例失败" + e.toString());
        }
    }

    protected BusinessException(int code, String msg) {
        this(code, msg, code != 0);
    }

    protected BusinessException(int code, String msg, boolean isRollback) {
        super(msg);
        this.code = code;
        this.msg = msg == null ? "" : msg;
    }
}

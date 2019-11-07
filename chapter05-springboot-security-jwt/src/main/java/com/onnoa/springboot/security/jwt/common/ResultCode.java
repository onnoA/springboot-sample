package com.onnoa.springboot.security.jwt.common;


import com.onnoa.springboot.security.jwt.IErrorCode;

/**
 * @Description: 枚举常用API操作码
 * @Author: onnoA
 * @Date: 2019/11/4 11:12
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}

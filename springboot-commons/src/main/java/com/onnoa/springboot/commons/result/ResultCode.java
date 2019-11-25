package com.onnoa.springboot.commons.result;


/**
 * @Description: 枚举通用的API操作码
 * @Author: onnoA
 * @Date: 2019/11/22 16:23
 */
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数检验失败"),
    VERIFICATION_CODE_EXPIRED(409, "验证码已过期"),
    VERIFICATION_CODE_ERROR(410, "验证码错误");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

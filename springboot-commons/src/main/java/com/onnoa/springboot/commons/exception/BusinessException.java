package com.onnoa.springboot.commons.exception;

/**
 * @Description: 业务异常的统一封装
 * @Author: onnoA
 * @Date: 2019/11/28 14:23
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -8968515887200000506L;

    private int code;
    private String msg;

    public BusinessException(String errMsg) {
        super(errMsg);
        this.msg = errMsg;
        this.code = 0;
    }

    public BusinessException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMessage());
        this.msg = exceptionEnums.getMessage();
        this.code = exceptionEnums.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

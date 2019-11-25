package com.onnoa.springboot.commons.result;

import java.io.Serializable;

/**
 * @Description: 封装通用的返回结果
 * @Author: onnoA
 * @Date: 2019/11/22 16:05
 */
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = -2729578054594422687L;


    private int code;
    private String msg;
    private T data;

    protected ResultBean() {
        super();
    }

    private ResultBean(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultBean buildResultBeanVO(ResultCode resultCode, Object data) {
        return new ResultBean<>(resultCode.getCode(), resultCode.getMsg(), data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.onnoa.springboot.security.jwt;

/**
 * @Description: 封装API的错误码
 * @Author: onnoA
 * @Date: 2019/11/4 11:13
 */
public interface IErrorCode {

    long getCode();

    String getMessage();
}

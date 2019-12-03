package com.onnoa.springboot.redis.interceptor;

import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.response.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 全局异常处理
 * @Author: onnoA
 * @Date: 2019/12/2 16:21
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    //指定拦截异常的类型
    @ExceptionHandler(BusinessException.class)
    public ResultBean globalExceptionHandler(BusinessException ex){
        log.error("异常拦截:{},异常信息:{}",ex,ex.getMsg());
        return new ResultBean<>(ex);
    }

    //指定拦截异常的类型
    @ExceptionHandler(RuntimeException.class)
    public ResultBean handle(RuntimeException ex){
        log.error("运行时异常:{},异常信息:{}",ex,ex.getMessage());
        return new ResultBean<>(ex);
    }

    //指定拦截异常的类型
    @ExceptionHandler(Exception .class)
    public ResultBean globalExceptionHandler(Exception  ex){
        log.error("异常:{},异常信息:{}",ex,ex.getMessage());
        return new ResultBean<>(ex);
    }





}

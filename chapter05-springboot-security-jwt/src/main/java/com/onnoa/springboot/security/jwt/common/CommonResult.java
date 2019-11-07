package com.onnoa.springboot.security.jwt.common;


import com.onnoa.springboot.security.jwt.IErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 自定义通用的返回结果
 * @Author: onnoA
 * @Date: 2019/11/4 11:00
 */
@Getter
@Setter
public class CommonResult<T> {

    private long code;
    private String message;
    private T data;

    protected CommonResult(){

    }

    protected CommonResult(long code,String message,T data){
        this.code = code;
        this.message = message;
        this.data =data;
    }

    /**
     * 功能描述: 成功返回结果
     * @param data 获取的数据
     * @auther: onnoA
     * @date: 2019/11/4 11:20
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 功能描述: 成功的返回结果
     * @param data 获取的数据
     * @param message 提示信息
     * @auther: onnoA
     * @date: 2019/11/4 11:20
     */
    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 功能描述: 失败返回结果
     * @param errorCode 错误码
     * @auther: onnoA
     * @date: 2019/11/4 11:23
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<T>(errorCode.getCode(),errorCode.getMessage(),null);
    }

    /**
     * 功能描述: 失败返回结果
     * @param message 错误提示信息
     * @auther: onnoA
     * @date: 2019/11/4 11:25
     */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),message,null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 功能描述: 参数校验失败返回结果
     * @param message 提示信息
     * @auther: onnoA
     * @date: 2019/11/4 11:29
     */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(),message,null);
    }

    /**
     * 功能描述: 未登录返回结果
     * @param data 返回数据
     * @auther: onnoA
     * @date: 2019/11/4 11:31
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(),data);
    }

    /**
     * 功能描述: 未授权返回结果
     * @param data 返回数据
     * @auther: onnoA
     * @date: 2019/11/4 11:32
     */
    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),data);
    }
}

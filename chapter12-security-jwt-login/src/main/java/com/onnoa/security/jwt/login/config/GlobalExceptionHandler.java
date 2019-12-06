package com.onnoa.security.jwt.login.config;

import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.response.ResultBean;
import com.onnoa.utils.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description: 自定义全局异常处理
 * @Author: onnoA
 * @Date: 2019/12/6 09:11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResultBean businessExceptionHandler(BusinessException ex) {
        LOGGER.error(ex.toString(), ex);
        return new ResultBean(ex.getCode(), ex.getMsg(), null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResultBean accessDeniedExceptionHandler(AccessDeniedException accEx){
        LOGGER.error(accEx.toString(),accEx.getMessage());
        return new ResultBean(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMsg(),null);
    }

    @ExceptionHandler(Exception.class)
    public ResultBean excetion(Throwable e) {
        LOGGER.error(e.getMessage(), e);
        return new ResultBean(500, "系统不舒服，请等待！", null);
    }
}

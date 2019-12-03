package com.onnoa.springboot.redis.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 拦截器
 * @Author: onnoA
 * @Date: 2019/12/2 17:55
 */
public class MyHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(handler instanceof HandlerMethod);
        String handlerValue = handler.toString();
        String[] split = handlerValue.split(handlerValue);
        String methodName = split[split.length - 1];
        System.out.println(methodName);
        System.out.println("请求方式:" + request.getMethod());
        System.out.println("请求路径:" + request.getRequestURL());
        System.out.println("请求项目相对路径:" + request.getRequestURI());
        System.out.println("授权类型:" + request.getAuthType());
        return true;
    }

    public static String getShortMethodName(String fullName) {
        int openBracketIdx = fullName.indexOf("(");
        String methodNameWithoutParam = fullName.substring(0, openBracketIdx);
        String[] arrays = methodNameWithoutParam.split("\\.");
        int length = arrays.length;
        return arrays[length - 2] + "." + arrays[length - 1];
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.onnoa.dynamic.proxy.jdk;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: jdk 动态代理类
 * @Author: onnoA
 * @Date: 2019/11/19 15:11
 */
public class JDKDynamicProxy implements InvocationHandler {

    // 需要被代理的对象
    private ProxyTarget proxyTarget;

    public JDKDynamicProxy(ProxyTarget proxyTarget) {
        this.proxyTarget = proxyTarget;
    }


    public IJDKDynamicProxy createProxy(){
        return (IJDKDynamicProxy) Proxy.newProxyInstance(proxyTarget.getClass().getClassLoader(),proxyTarget.getClass().getInterfaces(),this);
    }

    /**
     * @param proxyObj 指代理类
     * @param method 被代理的方法
     * @param args 该方法的参数数组
     * @return
     * @date 2019/12/18 14:55
     */
    @Override
    public Object invoke(Object proxyObj, Method method, Object[] args) throws Throwable {
        // 执行切面增强
        if ("proxyMethod".equals(method.getName())) {
            System.out.println("执行切面前置增强===========> " + method.getName());
            Object result = method.invoke(proxyTarget, args);

            System.out.println("执行切面后置增强===========> " + method.getName());
            return result;
        }
        // 如果不需要执行切面增强则执行原方法
        return method.invoke(proxyTarget, args);
    }

    public static void main(String[] args) {
        ProxyTarget proxyTarget = new ProxyTarget();
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(proxyTarget);
        IJDKDynamicProxy proxy = jdkDynamicProxy.createProxy();
        proxy.proxyMethod("小马",18);
        System.out.println(proxy.otherMethod());
    }
}

package com.onnoa.dynamic.proxy.jdk;

/**
 * @Description: jdk 动态代理的接口，被代理的类必须要实现改接口
 * @Author: onnoA
 * @Date: 2019/11/19 15:06
 */
public interface IJDKDynamicProxy {

    public String proxyMethod(String name,Integer age);

    public String otherMethod();
}

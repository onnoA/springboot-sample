package com.onnoa.dynamic.proxy.jdk;

/**
 * @Description: 被代理的对象
 * @Author: onnoA
 * @Date: 2019/11/19 15:08
 */
public class ProxyTarget implements IJDKDynamicProxy {


    @Override
    public String proxyMethod(String name, Integer age) {
        String str = "我的名字叫" + name + ",今年" + age;
        System.out.println("我的名字叫" + name + ",今年" + age);
        return str;
    }

    @Override
    public String otherMethod() {
        return null;
    }
}

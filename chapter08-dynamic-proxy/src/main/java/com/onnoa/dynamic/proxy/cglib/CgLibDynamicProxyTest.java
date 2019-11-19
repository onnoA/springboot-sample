package com.onnoa.dynamic.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Description: 测试类
 * @Author: onnoA
 * @Date: 2019/11/19 16:59
 */
public class CgLibDynamicProxyTest {

    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer 对象的父类
        enhancer.setSuperclass(ProxyTarget2.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new CgLibDynamicProxyMethodInterceptor());
        // 创建代理对象
        ProxyTarget2 proxyTarget2 = (ProxyTarget2) enhancer.create();
        // 通过代理对象调用目标方法
        proxyTarget2.cry("失恋");
        proxyTarget2.sayHi("zs");

    }
}

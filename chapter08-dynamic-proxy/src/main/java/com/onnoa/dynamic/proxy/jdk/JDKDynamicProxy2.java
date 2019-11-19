package com.onnoa.dynamic.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/19 15:41
 */
public class JDKDynamicProxy2 {

    private ProxyTarget proxyTarget = new ProxyTarget();

    public Object invokeProxyTarget() {
        // (proxy, method, args) -> {} === 》 箭头函数
        return Proxy.newProxyInstance(proxyTarget.getClass().getClassLoader(), proxyTarget.getClass().getInterfaces(), (proxyObj, method, args) -> {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 执行切面增强
            if ("proxyMethod".equals(method.getName())) {
                System.out.println("执行切面前置增强===========> " + method.getName());
                Object result = method.invoke(proxyTarget, args);
                System.out.println("执行切面后置增强===========> " + method.getName());
                return result;
            }
            // 执行原方法
            return method.invoke(proxyTarget, args);
//            }
        });
    }

    public static void main(String[] args) {
        JDKDynamicProxy2 proxy = new JDKDynamicProxy2();
        IJDKDynamicProxy obj = (IJDKDynamicProxy) proxy.invokeProxyTarget();
        obj.proxyMethod("马云", 18);

    }
}

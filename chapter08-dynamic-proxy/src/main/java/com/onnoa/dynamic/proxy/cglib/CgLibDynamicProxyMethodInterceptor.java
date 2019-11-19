package com.onnoa.dynamic.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: 自定义CgLibDynamicProxyMethodInterceptor 实现 MethodInterceptor 接口并重写 intercept 方法
 * 实现CGLIB动态代理必须实现MethodInterceptor(方法拦截器)接口
 * <p>
 * public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,
 * MethodProxy proxy) throws Throwable;
 * <p>
 * 这个接口只有一个intercept()方法，这个方法有4个参数：
 * <p>
 * 1）obj表示增强的对象，即实现这个接口类的一个对象；
 * <p>
 * 2）method表示要被拦截的方法；
 * <p>
 * 3）args表示要被拦截方法的参数；
 * <p>
 * 4）proxy表示要触发父类的方法对象；
 * @Author: onnoA
 * @Date: 2019/11/19 16:54
 */
public class CgLibDynamicProxyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if ("sayHi".equals(method.getName())) {
            System.out.println("执行前置增强===》" + method.getName());
            String name = o.getClass().getName();
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("执行后置增强===》" + method.getName());
            return result;
        } else if ("cry".equals(method.getName())) {
            System.out.println("执行前置增强===》" + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("执行后置增强===》" + method.getName());
            return result;
        }
        return method.invoke(o, objects);
    }
}

package com.onnoa.dynamic.proxy.cglib;

/**
 * @Description: 被代理的对象
 * @Author: onnoA
 * @Date: 2019/11/19 16:50
 */
public class ProxyTarget2 {

    public String sayHi(String name){
        System.out.println("我是:"+name+",你好啊！");
        String str = "我是:"+name+",你好啊！";
        return str;
    }

    /**
     * 功能描述:
     * @auther: onnoA
     * @date: 2019/11/19 17:15
     */
    final public String cry(String reason){
        System.out.println("因为"+reason+"哭！");
        return "因为"+reason+"哭！";
    }

}

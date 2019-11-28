package com.onnoa.springboot.rocketmq.email.start;

import com.onnoa.springboot.rocketmq.email.controller.EmailSendController;
import com.onnoa.springboot.rocketmq.email.entity.EmailEntity;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/28 15:54
 */
public class EmailSendStart {
    public static void main(String[] args) {
        EmailEntity email = new EmailEntity();
        email.setUserName("690569177@qq.com");
        email.setPassword("qtbefwmpyhkrbdbd");
        email.setHost("smtp.qq.com");
        email.setPort(25);
        email.setFromAddress("690569177@qq.com");
        email.setToAddress("onnoaheng@163.com");
        email.setSubject("这是一封测试邮件!!!!");
        email.setContext("看看这是什么");
        email.setContextType("text/html;charset=utf-8");
        boolean flag = EmailSendController.EmailSendTest(email);
        System.err.println("邮件发送结果==" + flag);

    }
}

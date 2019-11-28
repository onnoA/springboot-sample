package com.onnoa.springboot.rocketmq.email.controller;

import com.onnoa.springboot.rocketmq.email.constant.VerifyEmail;
import com.onnoa.springboot.rocketmq.email.entity.EmailEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

/**
 * @Description: 邮件发送Controller
 * @Author: onnoA
 * @Date: 2019/11/28 15:45
 */
@RestController
public class EmailSendController {
    public static boolean EmailSendTest(EmailEntity emailEntity){
        try {
            //属性mail.transport.protocol设置要使用的邮件协议
            //配置文件
            Properties properties = new Properties();
            //属性mail.smtp.auth设置发送时是否校验用户名和密码
            properties.put("mail.smtp.auth", "true");
            //属性mail.host表示发送服务器的邮件服务器地址
            properties.put("mail.smtp.host", emailEntity.getHost());
            properties.put("mail.smtp.port", 25);
            properties.put("mail.smtp.starrttls.enable", "true");
            //创建会话
            VerifyEmail verifyEmail = new VerifyEmail(emailEntity.getUserName(), emailEntity.getPassword());
            Session mailSession = Session.getInstance(properties, verifyEmail);
            mailSession.setDebug(true);
            //创建信息对象
            Message message = new MimeMessage(mailSession);
            InternetAddress from = new InternetAddress(emailEntity.getFromAddress());
            InternetAddress to = new InternetAddress(emailEntity.getToAddress());
            //设置邮件信息的来源
            message.setFrom(from);
            //设置邮件的接收者
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            message.setSubject(emailEntity.getSubject());
            //设置邮件发送日期
            message.setSentDate(new Date());
            //设置邮件内容
            message.setContent(emailEntity.getContext() , emailEntity.getContextType());
            message.saveChanges();
            //发送邮件
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailEntity.getHost(), emailEntity.getUserName(), emailEntity.getPassword());
            System.out.println("发送:" + transport);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("success");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("fial...");
            return false;

        }
    }

}

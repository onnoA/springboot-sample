package com.onnoa.security.jwt.login.utils;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/17 17:24
 */
@Configuration
public class WeChatPushUtils implements ApplicationContextAware {

    private static WxMpService wxMpService;

    private static WxCpService wxCpService;

    public static boolean  sendMpKefuTextMessage(String text, String openId) throws WxErrorException {
        WxMpKefuMessage message = new WxMpKefuMessage();
        message.setMsgType(WxConsts.KefuMsgType.TEXT);
        message.setToUser(openId);
        message.setContent(text);
        return wxMpService.getKefuService().sendKefuMessage(message);
    }

    public static void sendCpKefuTextMessage(String text,String userId) throws WxErrorException {
        WxCpMessage wxCpMessage = WxCpMessage
                .TEXT().agentId(wxCpService.getWxCpConfigStorage().getAgentId())
                .toUser(userId)
                .content(text)
                .build();
        wxCpService.messageSend(wxCpMessage);
        System.out.println(wxCpService.messageSend(wxCpMessage));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        wxMpService= (WxMpService) applicationContext.getBean(WxMpServiceHttpClientImpl.class);
        wxCpService = (WxCpService)applicationContext.getBean(WxCpServiceImpl.class);
    }
}

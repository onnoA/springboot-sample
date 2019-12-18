package com.onnoa.security.jwt.login.utils;

import com.onnoa.security.jwt.login.mapper.UmsWeChatConfigMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/17 16:51
 */
@Component
public class WeChatReminderUtil {

    @Autowired
    private UmsWeChatConfigMapper umsWeChatConfigMapper;

    public void sendCpTextMessage(String text) {
        List<String> list =
                umsWeChatConfigMapper.selectAllUseableCpReminder();
        if (CollectionUtils.isNotEmpty(list)) {
            String collect = list.stream().collect(Collectors.joining("|"));
            try {
                WeChatPushUtils.sendCpKefuTextMessage(text, collect);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
    }

    /*public boolean sendMpMessage(String text) {
        List<String> list =
                umsWeChatConfigMapper.selectAllUseableCpReminder();
        if (CollectionUtils.isNotEmpty(list)) {
            String collect = list.stream().collect(Collectors.joining("|"));
            try {
                return WeChatPushUtils.sendMpKefuTextMessage(text, collect);
            } catch (WxErrorException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }*/

    /*@Async
    public void sendAsyncCpTextMessage(String text) {
        List<String> list =
                umsWeChatConfigMapper.selectAllUseableCpReminder();
        if (CollectionUtils.isNotEmpty(list)) {
            String collect = list.stream().collect(Collectors.joining("|"));
            try {
                WeChatPushUtils.sendCpKefuTextMessage(text, collect);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
    }*/


}

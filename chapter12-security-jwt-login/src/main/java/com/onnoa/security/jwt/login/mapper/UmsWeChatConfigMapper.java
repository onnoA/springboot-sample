package com.onnoa.security.jwt.login.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.security.jwt.login.domain.UmsWeChatConfig;
import com.onnoa.security.jwt.login.enums.AdminStatusEnums;
import com.onnoa.security.jwt.login.enums.WechatTypeEnums;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/17 16:55
 */
public interface UmsWeChatConfigMapper extends BaseMapper<UmsWeChatConfig> {

    public default List<String> selectAllUseableCpReminder() {
        List<UmsWeChatConfig> list = this.selectList(new QueryWrapper<UmsWeChatConfig>()
                .eq("status", AdminStatusEnums.NORMAL.getStatus())
                .eq("wechat_type", WechatTypeEnums.WECHAT_CP.getCode()));

        return list.stream().map(UmsWeChatConfig::getWechatId).collect(Collectors.toList());
    }

}

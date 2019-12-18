package com.onnoa.security.jwt.login.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/17 16:59
 */
@AllArgsConstructor
@Getter
public enum WechatTypeEnums {
    WECHAT_CP("企业微信","CP"),
    WECHAT_MP("微信公众号","MP");

    private String msg;
    private String code;
}

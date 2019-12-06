package com.onnoa.security.jwt.login.enums;

/**
 * @Description: 帐号启用状态：0->禁用；1->启用
 * @Author: onnoA
 * @Date: 2019/12/4 17:00
 */
public enum AdminStatusEnums {

    // 禁用
    FORBIDDEN(0),

    // 正常
    NORMAL(1);

    private final Integer status;

    AdminStatusEnums(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}

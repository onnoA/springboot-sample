package com.onnoa.springboot.security.jwt.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户登录参数
 * @Author: onnoA
 * @Date: 2019/11/4 15:52
 */
@Data
public class UmsAdminDto implements Serializable {

    private String username;
    private String password;
}

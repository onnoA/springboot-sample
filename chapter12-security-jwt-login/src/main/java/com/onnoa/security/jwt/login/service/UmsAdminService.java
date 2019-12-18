package com.onnoa.security.jwt.login.service;

import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:30
 */
public interface UmsAdminService extends IService<UmsAdmin> {

    /**
     * 功能描述: 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     * @date 2019/12/4 17:20
     */
    String login(String username, String password);

    /**
     * 功能描述: 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户对象
     * @date 2019/12/5 10:00
     */
    UmsAdmin getAdminByUsername(String username);

    List<UmsAdmin> getByUsername(String admin);
}

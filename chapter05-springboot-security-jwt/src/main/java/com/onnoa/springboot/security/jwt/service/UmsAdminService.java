package com.onnoa.springboot.security.jwt.service;

import com.onnoa.springboot.security.jwt.domain.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.onnoa.springboot.security.jwt.domain.UmsPermission;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/4 10:52
 */
public interface UmsAdminService extends IService<UmsAdmin> {

    /**
     * 功能描述: 根据用户名获取用户信息
     * @param username 用户名
     * @auther: onnoA
     * @date: 2019/11/4 14:38
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 功能描述: 根据用户id获取用户权限
     * @param id
     * @auther: onnoA
     * @date: 2019/11/4 14:40
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 功能描述: 登录功能
     * @param username 用户名
     * @param password 密码
     * @return  String 生成的JWT的token
     * @auther: onnoA
     * @date: 2019/11/4 15:57
     */
    String login(String username, String password, HttpServletRequest request);
}

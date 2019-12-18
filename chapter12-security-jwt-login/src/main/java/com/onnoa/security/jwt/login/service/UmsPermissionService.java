package com.onnoa.security.jwt.login.service;

import com.onnoa.security.jwt.login.domain.UmsPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
public interface UmsPermissionService extends IService<UmsPermission> {

    /**
     * 功能描述: 根据用户id获取用户权限
     * @param adminId 用户id
     * @return 用户权限列表
     * @date 2019/12/4 16:14
     */
    List<UmsPermission> getPermissionByAdminId(Long adminId);
}

package com.onnoa.security.jwt.login.service;

import com.onnoa.security.jwt.login.domain.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
public interface UmsRoleService extends IService<UmsRole> {

    /**
     * 功能描述: 根据用户id获取用户角色
     * @param adminId 用户id
     * @return 用户角色列表
     * @date 2019/12/6 11:43
     */
    List<UmsRole> getRoleByAdminId(Long adminId);
}

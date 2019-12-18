package com.onnoa.security.jwt.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.security.jwt.login.domain.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    /**
     * 功能描述: 根据用户id获取用户权限
     * @param adminId 用户id
     * @return 权限列表
     * @date 2019/12/4 16:16
     */
    List<UmsPermission> getPermissionByAdminId(@Param("adminId") Long adminId);
}

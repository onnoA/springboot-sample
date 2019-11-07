package com.onnoa.springboot.security.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.springboot.security.jwt.domain.UmsAdmin;
import com.onnoa.springboot.security.jwt.domain.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/4 10:52
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 功能描述: 根据用户id获取用户的所有权限
     * @param adminId
     * @auther: onnoA
     * @date: 2019/11/4 14:42
     */
    //List<UmsPermission> getPermissionList(@Param("id") Long adminId);
}

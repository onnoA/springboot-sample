package com.onnoa.security.jwt.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.security.jwt.login.domain.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
    List<UmsRole> getRoleByAdminId(@Param("adminId") Long adminId);
}

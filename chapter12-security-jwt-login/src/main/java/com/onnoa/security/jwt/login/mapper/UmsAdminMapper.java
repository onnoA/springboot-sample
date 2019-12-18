package com.onnoa.security.jwt.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.domain.UmsRole;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:30
 */
@MapperScan
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    List<UmsRole> getRoleByAdminId(@Param("adminId") Long adminId);
}

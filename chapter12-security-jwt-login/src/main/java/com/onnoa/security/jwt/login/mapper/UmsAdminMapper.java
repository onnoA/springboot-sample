package com.onnoa.security.jwt.login.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.domain.UmsRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:30
 */
@MapperScan
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    //@Select("select * from ums_admin where username=#{username}")
    /*default List<UmsAdmin> getByUsername(@Param(value = "username") String admin) {
        QueryWrapper<UmsAdmin> qw = new QueryWrapper<>();
        qw.eq("username", admin);
        return this.selectList(qw);
    }*/

    List<UmsAdmin> getByUsername(@Param(value = "username") String admin);
}

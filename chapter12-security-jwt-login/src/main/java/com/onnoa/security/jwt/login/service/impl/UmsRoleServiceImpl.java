package com.onnoa.security.jwt.login.service.impl;

import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.mapper.UmsAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.security.jwt.login.mapper.UmsRoleMapper;
import com.onnoa.security.jwt.login.domain.UmsRole;
import com.onnoa.security.jwt.login.service.UmsRoleService;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    private UmsRoleMapper roleMapper;

    @Override
    public List<UmsRole> getRoleByAdminId(Long adminId) {
        return roleMapper.getRoleByAdminId(adminId);
    }
}

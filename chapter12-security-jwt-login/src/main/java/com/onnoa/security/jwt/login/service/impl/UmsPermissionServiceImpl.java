package com.onnoa.security.jwt.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.security.jwt.login.mapper.UmsPermissionMapper;
import com.onnoa.security.jwt.login.domain.UmsPermission;
import com.onnoa.security.jwt.login.service.UmsPermissionService;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements UmsPermissionService {

    @Resource
    private UmsPermissionMapper permissionMapper;

    @Override
    public List<UmsPermission> getPermissionByAdminId(Long adminId) {
        return permissionMapper.getPermissionByAdminId(adminId);
    }
}

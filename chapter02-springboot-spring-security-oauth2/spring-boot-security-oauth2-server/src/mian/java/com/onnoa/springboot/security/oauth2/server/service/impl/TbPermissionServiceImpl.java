package com.onnoa.springboot.security.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.springboot.security.oauth2.server.domain.TbPermission;
import com.onnoa.springboot.security.oauth2.server.mapper.TbPermissionMapper;
import com.onnoa.springboot.security.oauth2.server.service.TbPermissionService;

import java.util.List;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/1 10:28
 */
@Service
public class TbPermissionServiceImpl extends ServiceImpl<TbPermissionMapper, TbPermission> implements TbPermissionService{

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> getByUserId(Long userId) {
        List<TbPermission> permissionList = tbPermissionMapper.getByUserId(userId);
        return permissionList;
    }
}

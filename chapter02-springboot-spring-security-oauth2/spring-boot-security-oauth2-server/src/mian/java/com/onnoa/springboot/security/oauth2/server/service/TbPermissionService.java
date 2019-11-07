package com.onnoa.springboot.security.oauth2.server.service;

import com.onnoa.springboot.security.oauth2.server.domain.TbPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/1 10:28
 */
public interface TbPermissionService extends IService<TbPermission>{

    /**
     * 功能描述: 根据用户id获取用户权限
     * @param: id 用户id
     * @auther: onnoA
     * @date: 2019/11/1 10:29
     */
    List<TbPermission> getByUserId(Long userId);

}

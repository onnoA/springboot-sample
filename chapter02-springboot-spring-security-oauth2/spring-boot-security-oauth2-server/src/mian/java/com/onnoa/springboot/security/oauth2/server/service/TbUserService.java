package com.onnoa.springboot.security.oauth2.server.service;

import com.onnoa.springboot.security.oauth2.server.domain.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/1 09:16
 */
public interface TbUserService extends IService<TbUser>{

    /**
     * 功能描述: 根据用户名获取用户信息
     * @param: username 用户名
     * @auther: onnoA
     * @date: 2019/11/1 10:24
     */
    List<TbUser> getByUsername(String username);


}

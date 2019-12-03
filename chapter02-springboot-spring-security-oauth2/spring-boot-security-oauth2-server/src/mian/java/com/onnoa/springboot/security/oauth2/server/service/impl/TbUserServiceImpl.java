package com.onnoa.springboot.security.oauth2.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.springboot.security.oauth2.server.domain.TbUser;
import com.onnoa.springboot.security.oauth2.server.mapper.TbUserMapper;
import com.onnoa.springboot.security.oauth2.server.service.TbUserService;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/1 09:16
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService{

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> getByUsername(String username) {
        QueryWrapper<TbUser> qw = new QueryWrapper<>();
        qw.eq("username",username);
        List<TbUser> tbUserList = tbUserMapper.selectList(qw);
        return tbUserList;
    }
}

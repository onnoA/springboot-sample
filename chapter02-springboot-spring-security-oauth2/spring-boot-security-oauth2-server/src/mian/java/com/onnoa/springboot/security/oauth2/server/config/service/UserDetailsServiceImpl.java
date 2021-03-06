package com.onnoa.springboot.security.oauth2.server.config.service;

import com.onnoa.springboot.security.oauth2.server.domain.TbPermission;
import com.onnoa.springboot.security.oauth2.server.domain.TbUser;
import com.onnoa.springboot.security.oauth2.server.service.TbPermissionService;
import com.onnoa.springboot.security.oauth2.server.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/1 10:37
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<TbUser> tbUserList = tbUserService.getByUsername(username);
        if (CollectionUtils.isEmpty(tbUserList)) {
            throw new RuntimeException("用户不存在");
        }
        if(tbUserList.size()>1){
            throw new RuntimeException("用户名重复");
        }

        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();
        // 根据用户id获取用户权限
        List<TbPermission> tbPermissionList = tbPermissionService.getByUserId(tbUserList.get(0).getId());
        if(!CollectionUtils.isEmpty(tbPermissionList)){
            // 用户授权
            tbPermissionList.forEach(tbPermission -> {
                if (tbPermission != null && StringUtils.isNotBlank(tbPermission.getEnname())) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                    grantedAuthorityList.add(grantedAuthority);
                }
            });
        }


        return new User(tbUserList.get(0).getUsername(), tbUserList.get(0).getPassword(), grantedAuthorityList);

    }
}

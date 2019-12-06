package com.onnoa.security.jwt.login.config.security.service;

import com.google.common.collect.Lists;
import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.domain.UmsPermission;
import com.onnoa.security.jwt.login.domain.UmsRole;
import com.onnoa.security.jwt.login.enums.AdminStatusEnums;
import com.onnoa.security.jwt.login.service.UmsAdminService;
import com.onnoa.security.jwt.login.service.UmsPermissionService;
import com.onnoa.security.jwt.login.service.UmsRoleService;
import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.exception.ExceptionEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:42
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsPermissionService permissionService;
    @Autowired
    private UmsRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        if (Objects.isNull(admin)) {
            throw new BusinessException(ExceptionEnums.USE_USERNAME_ERROR);
        }
        List<GrantedAuthority> authorityList = Lists.newArrayList();
        // 根据用户 id获取用户权限
        List<UmsPermission> permissionList = permissionService.getPermissionByAdminId(admin.getId());
        if (!CollectionUtils.isEmpty(permissionList)) {
            List<GrantedAuthority> permissionFilterList = permissionList.stream().filter(permission -> permission.getValue() != null)
                    .map(permission -> new SimpleGrantedAuthority(permission.getValue())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(permissionFilterList)) {
                authorityList.addAll(permissionFilterList);
            }
        }
        // 根据id获取用户角色
        List<UmsRole> roleList = roleService.getRoleByAdminId(admin.getId());
        if (!CollectionUtils.isEmpty(roleList)) {
            List<GrantedAuthority> roleFilterList = roleList.stream().filter(role -> role.getName() != null).map(permission -> new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(roleFilterList)) {
                authorityList.addAll(roleFilterList);
            }
        }
        return new UserDetails() {
            /**
             * 功能描述: 过滤出权限值不为空的权限列表
             * @param
             * @return
             * @date 2019/12/4 16:55
             */
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorityList
                        /*permissionList.stream().filter(permission -> permission.getValue() != null)
                        .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                        .collect(Collectors.toList())*/;
            }

            @Override
            public String getPassword() {
                return admin.getPassword();
            }

            @Override
            public String getUsername() {
                return admin.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return AdminStatusEnums.NORMAL.getStatus().equals(admin.getStatus());
            }
        };
    }
}

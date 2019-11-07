package com.onnoa.springboot.security.jwt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onnoa.springboot.security.jwt.domain.UmsAdminLoginLog;
import com.onnoa.springboot.security.jwt.domain.UmsPermission;
import com.onnoa.springboot.security.jwt.mapper.UmsAdminLoginLogMapper;
import com.onnoa.springboot.security.jwt.mapper.UmsPermissionMapper;
import com.onnoa.springboot.security.jwt.utils.IpUtil;
import com.onnoa.springboot.security.jwt.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.springboot.security.jwt.domain.UmsAdmin;
import com.onnoa.springboot.security.jwt.mapper.UmsAdminMapper;
import com.onnoa.springboot.security.jwt.service.UmsAdminService;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/4 10:52
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsPermissionMapper permissionMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin umsAdmin = adminMapper.selectOne(new QueryWrapper<UmsAdmin>().eq("username", username));
        return umsAdmin;

    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return permissionMapper.getPermissionList(adminId);
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        String token = null;
        // 密码在客户端加密后传递
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确，请重新输入!");
        }
        try {
            // 获取真正的用户权限 userDetails.getAuthorities()
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 获取该用户的上下文信息 授权操作
            // 通过调用SecurityContextHolder.getContext().setAuthentication(...)，参数传递authentication对象，来建立安全上下文（security context）
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username, request);
        } catch (AuthenticationException e) {
            LOGGER.error("登录异常:{}", e.getMessage());
        }

        return token;
    }

    /**
     * 功能描述: 记录登录信息日志
     *
     * @param username
     * @auther: onnoA
     * @date: 2019/11/4 16:09
     */
    private void insertLoginLog(String username, HttpServletRequest request) {
        UmsAdmin umsAdmin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(umsAdmin.getId());
        loginLog.setCreateTime(new Date());
        // 获取登录ip
        String ipAddr = IpUtil.getIpAddr(request);
        loginLog.setIp(ipAddr);
        LOGGER.info("用户登录的日志信息:{}", JSONObject.toJSON(loginLog));
        loginLogMapper.insert(loginLog);
    }
}

package com.onnoa.security.jwt.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onnoa.security.jwt.login.config.security.service.UserDetailsServiceImpl;
import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.exception.ExceptionEnums;
import com.onnoa.utils.jwt.JWTUtil;
import com.onnoa.utils.jwt.JWTUtil2;
import com.onnoa.utils.jwt.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.security.jwt.login.mapper.UmsAdminMapper;
import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.service.UmsAdminService;
import org.springframework.util.CollectionUtils;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:30
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private UmsAdminMapper adminMapper;

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
            if (!matches) {
                throw new BusinessException(ExceptionEnums.USER_PASSWORD_ERROR);
            }
            // 生成 UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = JwtTokenUtils.generateToken(userDetails);
        } catch (AuthenticationException ex) {
            LOGGER.error("登录异常:{}", ex.getMessage());
        }
        return token;
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        QueryWrapper<UmsAdmin> qw = new QueryWrapper<>();
        qw.eq("username", username);
        List<UmsAdmin> umsAdminList = adminMapper.selectList(qw);
        if (CollectionUtils.isEmpty(umsAdminList)) {
            throw new BusinessException(ExceptionEnums.USE_USERNAME_ERROR);
        }
        if (umsAdminList.size() > 1) {
            throw new BusinessException(ExceptionEnums.USER_EXIST_SAME_USERNAME);
        }
        return umsAdminList.get(0);
    }

    @Override
    public List<UmsAdmin> getByUsername(String admin) {
        return adminMapper.getByUsername(admin);
    }
}

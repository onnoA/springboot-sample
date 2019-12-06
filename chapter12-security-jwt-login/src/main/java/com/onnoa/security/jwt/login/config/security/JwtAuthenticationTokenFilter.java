package com.onnoa.security.jwt.login.config.security;

import com.onnoa.security.jwt.login.config.security.service.UserDetailsServiceImpl;
import com.onnoa.utils.jwt.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
 * 不能在类上加注解交由spring管理，否则该过滤器会执行两遍
 * 参考： https://www.yuque.com/gcdd1993/ygqnr5/uiflw3
 * @Author: onnoA
 * @Date: 2019/12/5 09:13
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtTokenUtils.TOKENHEADER);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(JwtTokenUtils.TOKENHEAD)) {
            // 截取Bearer后面的token
            String authToken = authHeader.substring(JwtTokenUtils.TOKENHEAD.length());
            String username = JwtTokenUtils.getUserNameFromToken(authToken);
            LOGGER.info("用户登录的完整Token:{},获取登录用户的用户名:{}", authHeader, username);
            // 获取登录后权限
            if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                //校验token是否有效，并校验其保存的用户信息是否正确
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 校验用户token是否有效 true:有效，false：过期
                boolean tokenIsNotExpired = JwtTokenUtils.validateToken(authToken, userDetails);
                // token 有效，为该请求装载 用户权限信息
                if (tokenIsNotExpired) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //new WebAuthenticationDetailsSource().buildDetails(request)
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("认证用户:{} " + username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        System.out.println("出去了 JwtAuthenticationTokenFilter");
        filterChain.doFilter(request, response);
    }
}

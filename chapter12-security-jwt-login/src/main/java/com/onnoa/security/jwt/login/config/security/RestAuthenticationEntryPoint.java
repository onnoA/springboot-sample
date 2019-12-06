package com.onnoa.security.jwt.login.config.security;

import cn.hutool.json.JSONUtil;
import com.onnoa.utils.response.ResultBean;
import com.onnoa.utils.response.ResultCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 当未登录或token失效时，返回JSON格式的结果；
 * @Author: onnoA
 * @Date: 2019/12/4 18:06
 */
@Configuration
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResultBean.buildResultBeanVO(ResultCode.UNAUTHORIZED, authException.getMessage())));
        response.getWriter().flush();

    }
}

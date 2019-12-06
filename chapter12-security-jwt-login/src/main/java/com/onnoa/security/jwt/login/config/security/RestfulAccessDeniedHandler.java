package com.onnoa.security.jwt.login.config.security;

import cn.hutool.json.JSONUtil;
import com.onnoa.utils.response.ResultBean;
import com.onnoa.utils.response.ResultCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 当访问接口没有权限时，自定义的返回结果
 * @Author: onnoA
 * @Date: 2019/12/4 18:03
 */
@Configuration
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResultBean.buildResultBeanVO(ResultCode.FORBIDDEN,accessDeniedException.getMessage())));
        response.getWriter().flush();
    }
}

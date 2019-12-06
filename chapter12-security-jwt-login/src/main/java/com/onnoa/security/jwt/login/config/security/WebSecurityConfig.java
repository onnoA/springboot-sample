package com.onnoa.security.jwt.login.config.security;

import com.onnoa.security.jwt.login.config.security.service.UserDetailsServiceImpl;
import com.onnoa.security.jwt.login.service.UmsAdminService;
import com.onnoa.security.jwt.login.service.UmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: SpringSecurity的配置
 * 相关依赖及方法说明
 * <p>
 * configure(HttpSecurity httpSecurity)：用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
 * configure(AuthenticationManagerBuilder auth)：用于配置UserDetailsService及PasswordEncoder；
 * RestfulAccessDeniedHandler：当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；
 * RestAuthenticationEntryPoint：当未登录或token失效时，返回JSON格式的结果；
 * UserDetailsService:SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；
 * UserDetails：SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现；
 * PasswordEncoder：SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；
 * JwtAuthenticationTokenFilter：在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
 * @Author: onnoA
 * @Date: 2019/12/4 15:15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsPermissionService permissionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return userDetailsService.loadUserByUsername(username);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // JwtAuthenticationTokenFilter作为拦截器拦截请求
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    // AuthenticationManager进行认证
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //post请求默认的都开启了csrf的模式，所有post请求都必须带有token之类的验证信息才可以进入登陆页面，这边是禁用csrf模式
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf()
                .disable()
                // 基于token，不用session
                .sessionManagement()
                // Session 创建策略
                // ALWAYS 总是创建 HttpSession
                // IF_REQUIRED Spring Security 只会在需要时创建一个 HttpSession
                // NEVER Spring Security 不会创建 HttpSession，但如果它已经存在，将可以使用 HttpSession
                // STATELESS Spring Security 永远不会创建 HttpSession，它不会使用 HttpSession 来获取 SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //表示以下配置的所有的get请求都不需要权限认证
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                )
                .permitAll()
                // 对登录注册允许无权限访问
                .antMatchers("/admin/login", "/admin/register","/admin/test")
                .permitAll()
                // 除上面的请求外，所有的请求均需验证
                .anyRequest()
                .authenticated();
        // 禁用缓存
        http.headers().cacheControl();
        // 添加jwt filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                // 当访问接口没有权限时，自定义的返回结果
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // 当未登录或token失效时，返回JSON格式的结果；
                .authenticationEntryPoint(restAuthenticationEntryPoint);

    }

    // 对登录用户进行认证 其实就是获取用户信息，以及对密码进行校验
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                // 密码加密方式
                .passwordEncoder(passwordEncoder());
    }


}

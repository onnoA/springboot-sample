package com.onnoa.springboot.security.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: JwtToken生成的工具类
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @Author: onnoA
 * @Date: 2019/11/4 10:08
 */
@Configuration
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 功能描述: 生成token
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:38
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    /**
     * 功能描述: 从token中获取JWT中的负载
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:43
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.error("JWT格式校验失败:{}", token);
        }
        return claims;
    }

    /**
     * 功能描述: 生成token 的过期时间
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:18
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 功能描述: 从token中获取登录用户名
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:20
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 功能描述: 验证token是否有效
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:21
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername());
    }

    /**
     * 功能描述: 判断token是否已经失效
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:23
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 功能描述: 从token中获取过期时间
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:24
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 功能描述: 根据用户信息生成token
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:35
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 功能描述: 根据token是否可以被刷新
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:36
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 功能描述: 刷新token
     *
     * @auther: onnoA
     * @date: 2019/11/4 10:37
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}


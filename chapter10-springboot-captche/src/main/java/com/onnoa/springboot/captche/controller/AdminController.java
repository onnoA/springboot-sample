package com.onnoa.springboot.captche.controller;

import com.alibaba.fastjson.JSON;
import com.onnoa.springboot.captche.common.result.ResultBean;
import com.onnoa.springboot.captche.common.result.ResultCode;
import com.onnoa.springboot.captche.common.util.IPUtil;
import com.onnoa.springboot.captche.common.util.RedisUtil;
import com.onnoa.springboot.captche.common.util.TokenUtil;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/22 10:39
 */
@RestController
@Slf4j
public class AdminController {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "/captche")
    public ResultBean captche() {
        return tokenUtil.createVerificationCode();

    }

    // @ResponseBody
    @GetMapping(value = "/login")
    public ResultBean login(@RequestParam String params,  HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(params)) {
            // 抛异常，参数不能为空
        }
        final Map param = JSON.parseObject(params, Map.class);
        final String jpgKey = (String) param.get("jpgKey");
        final String verificationCode = (String) param.get("verificationCode");
        final String redisVerificationCode = (String) redisUtil.get(jpgKey);
        if (!redisUtil.hasKey(jpgKey)) {
            // 验证码失效
            return ResultBean.buildResultBeanVO(ResultCode.VERIFICATION_CODE_EXPIRED, null);
        }
        if (!StringUtils.equalsIgnoreCase(verificationCode, redisVerificationCode)) {
            // 验证码不正确
            return ResultBean.buildResultBeanVO(ResultCode.VERIFICATION_CODE_ERROR, null);
        }


        return null;
    }

}


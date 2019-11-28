package com.onnoa.springboot.captche.controller;

import com.alibaba.fastjson.JSON;
import com.onnoa.springboot.captche.common.result.ResultBean;
import com.onnoa.springboot.captche.common.result.ResultCode;
import com.onnoa.springboot.captche.common.util.RedisUtil;
import com.onnoa.springboot.captche.common.util.TokenUtil;
import com.onnoa.springboot.captche.common.util.UuidUtil;
import com.onnoa.springboot.captche.common.util.VerifyCodeUtil;
import com.onnoa.springboot.captche.common.util.VerifyCodeUtil2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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

    @GetMapping(value = "/getVerifyCode2")
    public ResultBean getVerifyCode2(){
        Object[] obj = VerifyCodeUtil2.createImage(true);
        String uuid = UuidUtil.getUUID();
        redisUtil.set(uuid,obj[0],5*60L);
        Map<String, String> map = new HashMap<>();
        map.put("jpgKey",uuid);
        map.put("jpg", (String) obj[1]);
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, map);
    }

    @GetMapping(value = "/getVerifyCode")
    public ResultBean getVerifyCode() {
        try {
            // 获取验证码
            Map<String, String> codeMap = VerifyCodeUtil.getVerifyCode();
            String uuid = UuidUtil.getUUID();
            redisUtil.set(uuid,codeMap.get("code"),5*60L);
            codeMap.put("jpgKey",uuid);
            codeMap.remove("code");
            return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, codeMap);
        } catch (Exception e) {
            log.error("获取验证码异常");
            return ResultBean.buildResultBeanVO(ResultCode.FAIL, null);
        }

    }

    // @ResponseBody
    @GetMapping(value = "/login")
    public ResultBean login(@RequestParam String params, HttpServletRequest request, HttpServletResponse response) {
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


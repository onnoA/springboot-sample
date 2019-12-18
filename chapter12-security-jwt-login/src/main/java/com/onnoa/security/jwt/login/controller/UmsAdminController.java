package com.onnoa.security.jwt.login.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.domain.UmsPermission;
import com.onnoa.security.jwt.login.service.UmsAdminService;
import com.onnoa.security.jwt.login.service.UmsPermissionService;
import com.onnoa.security.jwt.login.utils.WeChatReminderUtil;
import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.exception.ExceptionEnums;
import com.onnoa.utils.jwt.JwtTokenUtils;
import com.onnoa.utils.response.ResultBean;
import com.onnoa.utils.response.ResultCode;
import com.onnoa.utils.utils.PropertiesUtil;
import com.onnoa.utils.utils.RedisUtil;
import com.onnoa.utils.utils.UuidUtil;
import com.onnoa.utils.utils.VerifyCodeUtil2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 17:12
 */
@RestController
@RequestMapping(value = "/admin")
public class UmsAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsPermissionService permissionService;
    @Autowired
    private Environment environment;
    @Autowired
    private WeChatReminderUtil weChatReminderUtil;

    @PostMapping(value = "mybatis")
    public ResultBean mybatis() {
        List<UmsAdmin> list = adminService.getByUsername("admin");
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, list);
    }


    @PostMapping(value = "/push")
    public ResultBean weChatPushMsg() {
        try {
            weChatReminderUtil.sendCpTextMessage("企业微信消息推送测试");
        } catch (Exception e) {
            return ResultBean.buildResultBeanVO(ResultCode.FAIL, "微信推送失败!");
        }
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, "微信推送成功!");

    }


    @PostMapping(value = "captche")
    public ResultBean captche() {
        System.out.println("test" + PropertiesUtil.getProperty("test"));
        System.out.println("配置" + PropertiesUtil.getProperty("appId"));
        System.out.println("mybatis" + PropertiesUtil.getProperty("mapper-locations"));
        Object[] obj = VerifyCodeUtil2.createImage(true);
        String uuid = UuidUtil.getUUID();
        redisUtil.set(uuid, obj[0], 5 * 600L);
        Map<String, String> map = new HashMap<>();
        map.put("jpgKey", uuid);
        map.put("jpg", (String) obj[1]);
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    //@PreAuthorize("hasAuthority('pms:brand:read') or hasRole('品牌管理员')")
    public ResultBean login(@RequestParam String params) {
        try {
            Map map = JSON.parseObject(params);
            String idStr = String.valueOf(map.get("id"));
            if (StringUtils.isBlank(idStr)) {
                throw new BusinessException(String.format(ExceptionEnums.PARAMETERS_EXCEPTION.getMessage(), "参数不能为空"));
            }
            String jpg = (String) map.get("jpg");
            String jpgKey = (String) map.get("jpgKey");
            if (!redisUtil.hasKey(jpgKey)) {
                throw new BusinessException(ExceptionEnums.USER_VERIFICATION_CODE_HAVE_EXPIRED);
            }
            String redisJpg = (String) redisUtil.get(jpgKey);
            if (!StringUtils.equalsIgnoreCase(redisJpg, jpg)) {
                throw new BusinessException(ExceptionEnums.USER_VERIFICATION_CODE_ERROR);
            }
            String username = (String) map.get("username");
            String password = (String) map.get("password");
            String token = adminService.login(username, password);
            LOGGER.info("根据token获取用户为:{}", JwtTokenUtils.getUserNameFromToken(token));
            Map<String, Object> tokenMap = Maps.newHashMap();
            tokenMap.put("token", token);
            tokenMap.put("tokenHead", JwtTokenUtils.TOKENHEAD);
            return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, tokenMap);
        } catch (Exception e) {
            return ResultBean.buildResultBeanVO(ResultCode.FAIL, e.getMessage());
        }
    }

    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('品牌管理员') ")
    public ResultBean test() {
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, null);
    }

    @PostMapping(value = "/permission")
    public ResultBean getPermissionList(@RequestParam String params) {
        Map map = JSON.parseObject(params);
        Long id = Long.valueOf((String) map.get("id"));
        List<UmsPermission> permissionList = permissionService.getPermissionByAdminId(id);
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, permissionList);
    }
}

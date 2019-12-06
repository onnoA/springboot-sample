package com.onnoa.security.jwt.login.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.onnoa.security.jwt.login.domain.UmsPermission;
import com.onnoa.security.jwt.login.service.UmsAdminService;
import com.onnoa.security.jwt.login.service.UmsPermissionService;
import com.onnoa.security.jwt.login.service.impl.UmsAdminServiceImpl;
import com.onnoa.utils.exception.BusinessException;
import com.onnoa.utils.exception.ExceptionEnums;
import com.onnoa.utils.jwt.JwtTokenUtils;
import com.onnoa.utils.response.ResultBean;
import com.onnoa.utils.response.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UmsAdminService adminService;
    @Autowired
    private UmsPermissionService permissionService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    //@PreAuthorize("hasAuthority('pms:brand:read') or hasRole('品牌管理员')")
    public ResultBean login(@RequestParam String params) {
        try {
            Map map = JSON.parseObject(params);
            String idStr = String.valueOf(map.get("id"));
            if (StringUtils.isBlank(idStr)) {
                throw new BusinessException(String.format(ExceptionEnums.PARAMETERS_EXCEPTION.getMessage(), "参数不能为空"));
            }
            Long id = Long.valueOf(idStr);
            LOGGER.info("id为:{}", id);
            String username = (String) map.get("username");
            String password = (String) map.get("password");
            String token = adminService.login(username, password);
            LOGGER.info("根据token获取用户为:{}", JwtTokenUtils.getUserNameFromToken(token));
            Map<String, Object> tokenMap = Maps.newHashMap();
            tokenMap.put("token", token);
            tokenMap.put("tokenHead", JwtTokenUtils.TOKENHEAD);
            return ResultBean.buildResultBeanVO(ResultCode.SUCCESS, tokenMap);
        } catch (Exception e) {
            return ResultBean.buildResultBeanVO(ResultCode.FAIL, null);
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

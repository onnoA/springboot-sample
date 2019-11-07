package com.onnoa.springboot.security.jwt.controller;

import com.onnoa.springboot.security.jwt.common.CommonResult;
import com.onnoa.springboot.security.jwt.domain.UmsAdmin;
import com.onnoa.springboot.security.jwt.domain.UmsPermission;
import com.onnoa.springboot.security.jwt.dto.UmsAdminDto;
import com.onnoa.springboot.security.jwt.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/4 15:48
 */
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "UmsAdminController",description = "后台用户管理接口")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "登录以后返回token")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UmsAdminDto umsAdminDto, HttpServletRequest request, BindingResult result) {
        String token = umsAdminService.login(umsAdminDto.getUsername(), umsAdminDto.getPassword(), request);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误，请重新输入！");
        }
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户所有权限")
    @ResponseBody
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.POST)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}

package com.onnoa.security.jwt.login.controller;

import com.onnoa.security.jwt.login.domain.UmsAdmin;
import com.onnoa.security.jwt.login.service.UmsAdminService;
import com.onnoa.security.jwt.login.service.UmsPermissionService;
import com.onnoa.utils.response.ResultBean;
import com.onnoa.utils.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/5 14:49
 */
@RestController
@RequestMapping(value = "/brand")
public class PmsBrandController {

    //@Autowired
    //private PmsBrandService brandService;

    @Autowired
    private UmsPermissionService permissionService;


    @GetMapping(value = "/read")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public ResultBean<List<UmsAdmin>> getBrandList() {
        return ResultBean.buildResultBeanVO(ResultCode.SUCCESS,permissionService.getPermissionByAdminId(3L));
    }

}

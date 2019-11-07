package com.onnoa.springboot.security.jwt.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.springboot.security.jwt.mapper.UmsPermissionMapper;
import com.onnoa.springboot.security.jwt.domain.UmsPermission;
import com.onnoa.springboot.security.jwt.service.UmsPermissionService;
/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/4 14:38
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements UmsPermissionService{

}

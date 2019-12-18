package com.onnoa.security.jwt.login.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.security.jwt.login.domain.UmsRolePermissionRelation;
import com.onnoa.security.jwt.login.mapper.UmsRolePermissionRelationMapper;
import com.onnoa.security.jwt.login.service.UmsRolePermissionRelationService;
/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/12/4 15:31
 */
@Service
public class UmsRolePermissionRelationServiceImpl extends ServiceImpl<UmsRolePermissionRelationMapper, UmsRolePermissionRelation> implements UmsRolePermissionRelationService{

}

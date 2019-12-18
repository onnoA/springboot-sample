package com.onnoa.security.jwt.login.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.security.jwt.login.mapper.UmsAdminRoleRelationMapper;
import com.onnoa.security.jwt.login.domain.UmsAdminRoleRelation;
import com.onnoa.security.jwt.login.service.UmsAdminRoleRelationService;
/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/12/4 15:31
 */
@Service
public class UmsAdminRoleRelationServiceImpl extends ServiceImpl<UmsAdminRoleRelationMapper, UmsAdminRoleRelation> implements UmsAdminRoleRelationService{

}

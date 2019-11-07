package com.onnoa.springboot.security.jwt.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.springboot.security.jwt.mapper.UmsAdminLoginLogMapper;
import com.onnoa.springboot.security.jwt.domain.UmsAdminLoginLog;
import com.onnoa.springboot.security.jwt.service.UmsAdminLoginLogService;
/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/4 16:11
 */
@Service
public class UmsAdminLoginLogServiceImpl extends ServiceImpl<UmsAdminLoginLogMapper, UmsAdminLoginLog> implements UmsAdminLoginLogService{

}

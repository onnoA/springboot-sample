package com.onnoa.springboot.security.oauth2.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.springboot.security.oauth2.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/1 10:28
 */
public interface TbPermissionMapper extends BaseMapper<TbPermission> {

    List<TbPermission> getByUserId(@Param("userId") Long userId);
}

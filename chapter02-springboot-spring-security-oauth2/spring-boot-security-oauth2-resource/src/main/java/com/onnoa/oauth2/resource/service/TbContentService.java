package com.onnoa.oauth2.resource.service;

import com.onnoa.oauth2.resource.domain.TbContent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/3 21:34
 */
public interface TbContentService extends IService<TbContent>{


    List<TbContent> selectAll();
}

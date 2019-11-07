package com.onnoa.oauth2.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onnoa.oauth2.resource.mapper.TbContentMapper;
import com.onnoa.oauth2.resource.domain.TbContent;
import com.onnoa.oauth2.resource.service.TbContentService;
/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/11/3 21:34
 */
@Service
public class TbContentServiceImpl extends ServiceImpl<TbContentMapper, TbContent> implements TbContentService{

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> selectAll() {
        List<TbContent> tbContentList = tbContentMapper.selectList(null);
        return tbContentList;
    }
}

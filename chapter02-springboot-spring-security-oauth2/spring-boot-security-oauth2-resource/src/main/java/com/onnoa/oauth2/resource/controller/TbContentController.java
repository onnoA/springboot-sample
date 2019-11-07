package com.onnoa.oauth2.resource.controller;

import com.onnoa.oauth2.resource.domain.TbContent;
import com.onnoa.oauth2.resource.dto.ResponseResult;
import com.onnoa.oauth2.resource.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/3 21:41
 */
@RestController
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    /**
     * 功能描述: 获取全部资源
     * @auther: onnoA
     * @date: 2019/11/3 21:47
     */
    @GetMapping("/")
    public ResponseResult<List<TbContent>> selectAll(){
        List<TbContent> tbContentList = tbContentService.selectAll();
        return new ResponseResult<List<TbContent>>(HttpStatus.OK.value(), HttpStatus.OK.toString(), tbContentList);
    }

    /**
     * 获取资源详情
     *
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    public ResponseResult<TbContent> getById(@PathVariable Long id) {
        return new ResponseResult<>(Integer.valueOf(HttpStatus.OK.value()), HttpStatus.OK.toString(), tbContentService.getById(id));
    }
}

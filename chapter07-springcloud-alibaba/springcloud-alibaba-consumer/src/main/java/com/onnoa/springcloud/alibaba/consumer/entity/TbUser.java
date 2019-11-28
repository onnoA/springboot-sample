package com.onnoa.springcloud.alibaba.consumer.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/28 17:18
 */
@Data
public class TbUser implements Serializable {

    private static final long serialVersionUID = -8048950189519392128L;

    private Integer id;
    private String username;
    private String email;

}

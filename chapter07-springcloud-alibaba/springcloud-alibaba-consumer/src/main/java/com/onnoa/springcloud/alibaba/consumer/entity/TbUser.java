package com.onnoa.springcloud.alibaba.consumer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/11/28 17:18
 */
//@TableName("tb_user")
@Data
public class TbUser implements Serializable {

    private static final long serialVersionUID = 4978928916244092281L;
    private long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private int deleted;

    private int version;

}

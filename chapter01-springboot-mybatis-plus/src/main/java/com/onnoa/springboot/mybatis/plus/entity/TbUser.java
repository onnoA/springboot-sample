package com.onnoa.springboot.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 11:26
 */
@TableName("tb_user")
@Data
public class TbUser implements Serializable {

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

package com.onnoa.security.jwt.login.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Description:
 * @Author:     onnoA
 * @Date:     2019/12/4 15:30
 */
@Data
@TableName(value = "ums_admin")
public class UmsAdmin implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    /**
     * 头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 备注信息
     */
    @TableField(value = "note")
    private String note;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @TableField(value = "status")
    private Integer status;
}

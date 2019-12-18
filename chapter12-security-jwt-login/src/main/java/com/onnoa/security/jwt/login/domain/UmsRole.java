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
 * @Date:     2019/12/4 15:31
 */
@Data
@TableName(value = "ums_role")
public class UmsRole implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 后台用户数量
     */
    @TableField(value = "admin_count")
    private Integer adminCount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "sort")
    private Integer sort;
}

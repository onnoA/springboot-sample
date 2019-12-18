package com.onnoa.security.jwt.login.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/4 15:31
 */
@Data
@TableName(value = "ums_admin_permission_relation")
public class UmsAdminPermissionRelation implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "admin_id")
    private Integer adminId;

    @TableField(value = "permission_id")
    private Long permissionId;

    @TableField(value = "type")
    private Integer type;
}

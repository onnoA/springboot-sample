package com.onnoa.security.jwt.login.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/12/17 16:55
 */
@Data
@TableName(value = "ums_wechat_config")
public class UmsWeChatConfig implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "wechat_id")
    private String wechatId;

    @TableField(value = "wechat_type")
    private String wechatType;

    private Integer status;

    @TableField(value = "create_time")
    private Date createTime;

}

package com.onnoa.springboot.security.jwt.domain;

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
 * @Date:     2019/11/4 16:11
 */
@Data
@TableName(value = "ums_admin_login_log")
public class UmsAdminLoginLog implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "admin_id")
    private Long adminId;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "ip")
    private String ip;

    @TableField(value = "address")
    private String address;

    /**
     * 浏览器登录类型
     */
    @TableField(value = "user_agent")
    private String userAgent;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ADMIN_ID = "admin_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IP = "ip";

    public static final String COL_ADDRESS = "address";

    public static final String COL_USER_AGENT = "user_agent";
}
package com.onnoa.springboot.security.jwt.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 用户登录参数
 * @Author: onnoA
 * @Date: 2019/11/4 15:52
 */
@Data
public class UmsAdminDto implements Serializable {

    private static final long serialVersionUID = -812066114929187587L;

    @NotBlank
    @Length(min = 3 ,message = "用户名长度至少为3位")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 5,message = "密码长度至少为5位")
    private String password;
}

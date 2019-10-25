package com.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * User: lanxinghua
 * Date: 2019/10/25 16:02
 * Desc: 修改用户名密码参数
 */
@Getter
@Setter
public class UpdateAdminPasswordParam {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}

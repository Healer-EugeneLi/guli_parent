package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: LoginVo
 * @Description: 登录包装类
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 20:50
 */
@Data
@ApiModel(value = "登录对象")
public class LoginVo {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;
}

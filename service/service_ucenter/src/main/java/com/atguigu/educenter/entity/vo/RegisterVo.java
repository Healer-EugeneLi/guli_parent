package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: RegisterVo
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 20:51
 */
@ApiModel(value = "注册对象")
@Data
public class RegisterVo {

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;
}

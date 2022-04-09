package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: LoginInfoVo
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/7
 * @Time: 11:44
 */
@Data
@ApiModel(value = "登录信息对象")
public class LoginInfoVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("age")
    private Integer age;

    @ApiModelProperty("avatar")
    private String avatar;

    @ApiModelProperty("nickname")
    private String nickname;

    @ApiModelProperty("sex")
    private Integer sex;




}

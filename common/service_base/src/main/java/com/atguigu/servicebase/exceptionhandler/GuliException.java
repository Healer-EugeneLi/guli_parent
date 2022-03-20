package com.atguigu.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: GuliException
 * @Description:自定义异常类
 * @Author EugeneLi
 * @Date: 2022/3/18
 * @Time: 16:30
 */
@Data
@AllArgsConstructor//生成有参数的构造方法
@NoArgsConstructor//生成无参数的构造方法
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码

    @ApiModelProperty(value = "异常信息")
    private String message;//异常信息


}

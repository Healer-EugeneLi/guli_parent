package com.atguigu.commonutils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: R
 * @Description: 统一返回结果类
 * @Author EugeneLi
 * @Date: 2022/3/17
 * @Time: 21:46
 */
@Data
public class R {


    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data=new HashMap<String,Object>();

    //把构造方法私有 为了让别人只能用下面的ok 或者error的方法  后面的方法返回this 可以实现链式编程
    private R(){

    }

    /**
     * 成功的静态方法
     * @return
     */
    public static R ok(){
        R r=new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }


    /**
     * 失败的静态方法
     * @return
     */
    public static R error(){

        R r=new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success){

        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }


    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key,Object value){

        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> map){

        this.setData(map);
        return this;
    }
}

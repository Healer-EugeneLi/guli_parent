package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: EduLoginController
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/20
 * @Time: 9:55
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {




    @ApiOperation("用户登录")
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","token");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public R getInfo(){

        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://avatars.githubusercontent.com/u/26404328?s=40&v=4");

    }
}

package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    private R login(@RequestBody LoginVo loginVo){

        String token=memberService.login(loginVo);
        System.out.println("登录成功");
        return R.ok().message("登录成功").data("token",token);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    private R register(@RequestBody RegisterVo registerVo){

        memberService.register(registerVo);
        System.out.println("注册成功");
        System.out.println("注册成功");
        return R.ok().message("注册成功");
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("auth/getLoginInfo")
    public R getToken(HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        LoginInfoVo loginInfoVo=memberService.getLoginVoInfo(memberId);
        System.out.println(loginInfoVo.toString());
        return R.ok().data("loginInfoVo",loginInfoVo);
    }

}


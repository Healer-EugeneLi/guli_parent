package com.atguigu.cms.controller;


import com.atguigu.cms.entity.CrmBanner;
import com.atguigu.cms.service.CrmBannerService;
import com.atguigu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 * 前台banner对应的接口
 * @author testjava
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/educms/bannerFront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("获取所有的banner")
    @GetMapping("listBanner")
    public R getBannerAll(){

        List<CrmBanner> list=crmBannerService.getBannerAll();
        System.out.println("获取所有的banner成功");
        return R.ok().data("list",list);
    }

}


package com.atguigu.cms.controller;


import com.atguigu.cms.entity.CrmBanner;
import com.atguigu.cms.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *banner后台对应的接口
 * @author testjava
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/educms/bannerAdmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;


    @ApiOperation("分页查询banner")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){


        Page<CrmBanner> crmBannerPage=new Page<>(page,limit);

        crmBannerService.page(crmBannerPage,null);

        return R.ok().data("items",crmBannerPage.getRecords()).data("total",crmBannerPage.getTotal());
    }

    @ApiOperation("新增banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner banner){

        boolean save = crmBannerService.save(banner);
        if (save==false)
            throw new GuliException(20001,"新增banner失败");
        return R.ok().message("新增banner成功");
    }

    @ApiOperation("删除banner")
    @DeleteMapping("remove/{id}")
    public R removeBanner(@PathVariable String id){

        boolean remove = crmBannerService.removeById(id);
        if (remove==false)
            throw new GuliException(20001,"删除banner失败");
        return R.ok().message("删除banner成功");
    }

    @ApiOperation("更新banner")
    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody CrmBanner banner){

        boolean update = crmBannerService.updateById(banner);
        if (update==false)
            throw new GuliException(20001,"更新banner失败");
        return R.ok().message("更新banner成功");
    }

    @ApiOperation("获取banner")
    @GetMapping("getBanner/{id}")
    public R getBannerById(@PathVariable String id){

        CrmBanner banner = crmBannerService.getById(id);
        if (banner==null)
            throw new GuliException(2001,"该id对应的banner不存在");
        return R.ok().message("获取banner成功");
    }

}


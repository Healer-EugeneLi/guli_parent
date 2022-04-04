package com.atguigu.vod.controller;

import com.atguigu.commonutils.R;
import com.atguigu.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: VodController
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/3
 * @Time: 21:26
 */
@Api("阿里云视频点播微服务")
@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {


    @Autowired
    private VodService vodService;

    @ApiOperation(("上传视频到阿里云"))
    @PostMapping("uploadVideo")
    public R uploadAlyVideo(MultipartFile file){


        String videoId=vodService.uploadAlyVideo(file);
        return R.ok().message("上传文件成功").data("videoId",videoId);
    }

}

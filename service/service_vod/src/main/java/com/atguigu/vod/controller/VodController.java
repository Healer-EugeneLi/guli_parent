package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtil;
import com.atguigu.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @ApiOperation("根据videoId删除视频")
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){

        vodService.deleteVideoByVideoId(videoId);
        return R.ok().message("删除视频成功:videoId为"+videoId);
    }

    @ApiOperation("删除多个视频")
    @DeleteMapping("deleteBatchVideo")
    public R deleteBatch(@RequestParam("videoIdList")List<String> videoList){

        vodService.deleteBatch(videoList);
        return R.ok();
    }

    @ApiOperation("根据视频id获取凭证")
    @GetMapping("getVideoAuth/{id}")
    public R getVideoAuth(@PathVariable String id){

        //初始化对象
        try {
            DefaultAcsClient defaultAcsClient = InitVodClient.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
            //设置id
            request.setVideoId(id);
            //返回response
            GetVideoPlayAuthResponse response = defaultAcsClient.getAcsResponse(request);
            //获取凭证
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new GuliException(20001,"获取视频凭证失败");
        }




    }
}

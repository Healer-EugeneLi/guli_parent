package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;


    //添加小节
    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){

        boolean save = eduVideoService.save(eduVideo);
        if (save==false)
            throw new GuliException(20001,"添加小节失败");
        return R.ok().message("添加小节成功");
    }

    @ApiOperation("通过id获取小节信息")
    @GetMapping("{videoId}")
    public R getVideoById(@PathVariable String videoId){

        EduVideo video = eduVideoService.getById(videoId);
        if (video==null)
            throw new GuliException(20001,"获取小节信息失败");

        return R.ok().data("video",video);
    }

    //删除小节
    // TODO: 2022/3/28 后面这个方法需要完善 删除小节的是 同时把里面视频删掉
    @ApiOperation("删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){


        boolean b = eduVideoService.removeById(id);
        if (b==false)
            throw new GuliException(20001,"删除小节失败");
        return R.ok().message("删除小节成功");
    }

    //更新小节信息
    @ApiOperation("更新小节")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){

        boolean update = eduVideoService.updateById(eduVideo);
        if (!update)
            throw new GuliException(20001,"更新小节信息失败");
        return R.ok().message("更新小节信息成功");
    }

}


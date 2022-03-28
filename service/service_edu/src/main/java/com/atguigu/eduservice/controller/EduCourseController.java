package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *把简介也放到这里来操作
 * @author testjava
 * @since 2022-03-26
 */
@Api("课程管理相关")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {


    @Autowired
    private EduCourseService courseService;

    @ApiOperation("添加课程信息")
    @PostMapping("addCourse")
    public R addCourse(@RequestBody CourseInfo courseInfo){

        String courseId=courseService.addCourse(courseInfo);
        return R.ok().message("添加课程信息成功").data("courseId",courseId);
    }

    @ApiOperation("根据课程id获取课程信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfoByCourseId(@PathVariable String courseId){

        CourseInfo courseInfo= courseService.getCourseInfoByCourseId(courseId);
        return R.ok().data("courseInfo",courseInfo);
    }

    @ApiOperation("修改课程信息")
    @PostMapping("updateCourse")
    public R updateCourseInfo(@RequestBody CourseInfo courseInfo){

        courseService.updateCourseInfo(courseInfo);
        return R.ok().message("修改课程信息成功");
    }

    @ApiOperation("根据课程id获取发布时的课程信息")
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPulishCourseInfo(@PathVariable String courseId){

        CoursePublishVo coursePublishVo=courseService.getPublishCourseInfo(courseId);
        return R.ok().data("coursePublishInfo",coursePublishVo);
    }


}


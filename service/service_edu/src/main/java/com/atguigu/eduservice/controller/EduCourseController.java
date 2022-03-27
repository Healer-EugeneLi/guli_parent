package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.service.EduCourseService;
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
}


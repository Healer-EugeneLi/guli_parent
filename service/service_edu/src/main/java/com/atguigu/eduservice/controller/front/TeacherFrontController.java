package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeacherFrontController
 * @Description:前台名师接口
 * @Author EugeneLi
 * @Date: 2022/4/8
 * @Time: 15:06
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;


    @ApiOperation("前端获取首页名师的接口")
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit){

        Page<EduTeacher> pageTeacher=new Page<>(page,limit);

        Map<String,Object> map= teacherService.getTeacherPageList(pageTeacher);
        return R.ok().data("map",map);
    }

    @ApiOperation("根据讲师id查询讲师基本信息")
    @GetMapping("getTeacherInfoById/{teacherId}")
    public R getTeacherInfoById(@PathVariable String teacherId){

        //获取讲师基本信息
        EduTeacher teacher = teacherService.getById(teacherId);


        //获取讲师上的课程
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courses = courseService.list(queryWrapper);

        return R.ok().data("teacher",teacher).data("courseList",courses);


    }

}

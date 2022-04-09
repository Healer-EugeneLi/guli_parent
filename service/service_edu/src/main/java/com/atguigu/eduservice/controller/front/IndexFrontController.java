package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: IndexFrontController
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 10:23
 *
 */

@RestController
@RequestMapping("/eduservice/indexFront")
@CrossOrigin
public class IndexFrontController {


    @Autowired
    private EduCourseService courseService;

    @Autowired
    public EduTeacherService teacherService;

    @ApiOperation("获取热门讲师、热门课程")
    @GetMapping("index")
    public R indexFront(){

        //获取8个热门课程
        QueryWrapper<EduCourse> courseWrapper=new QueryWrapper<>();
        courseWrapper.orderByAsc("id");
        courseWrapper.last("limit 8");
        List<EduCourse> courses = courseService.list(courseWrapper);

        //获取4个热门讲师
        QueryWrapper<EduTeacher> teacherWrapper=new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 8");
        List<EduTeacher> teachers = teacherService.list(teacherWrapper);

        return R.ok().data("courseList",courses).data("teacherList",teachers);
    }
}

package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("发布课程，修改课程状态")
    @PostMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){

        EduCourse course=new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");//更新状态
        boolean b = courseService.updateById(course);
        if (b==false)
            throw new GuliException(20001,"发布课程失败");
        return R.ok().message("发布课程成功");
    }

    @ApiOperation("获取所有课程信息")
    @GetMapping("findAll")
    public R getAllCourse(){

        List<EduCourse> list = courseService.list(null);

        return R.ok().message("获取所有课程成功").data("items",list);

    }

    @ApiOperation("分页查询所有课程")
    @GetMapping("pageCourse/{current}/{limit}")
    public R pageListCourse(@PathVariable long current,
                            @PathVariable long limit){

        Page<EduCourse> page=new Page<>(current,limit);

        courseService.page(page,null);

        long total = page.getTotal();//总记录数
        List<EduCourse> records = page.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("分页带查询所有课程")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageQuery(@PathVariable long current,
                     @PathVariable long limit,
                     @RequestBody CourseQuery courseQuery){

        Page<EduCourse> pageCourse=new Page<>(current,limit);

        courseService.pageQuery(pageCourse,courseQuery);

        List<EduCourse> records = pageCourse.getRecords();
        long total = pageCourse.getTotal();
        return R.ok().data("total",total).data("rows",records).message("分页带查询所有课程成功");

    }

    @ApiOperation("删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){

        //删除课程 需要先删除video中的记录 再删除chapter中的记录 最后删除course中的记录
        boolean result=courseService.removeCourseById(courseId);
        if (result==false)
            throw new GuliException(20001,"删除课程失败");
        return R.ok().message("删除课程成功");

    }


}


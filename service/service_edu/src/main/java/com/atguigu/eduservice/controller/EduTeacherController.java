package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-17
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;



    //查询所有讲师
    @GetMapping("findAll")
    @ApiOperation("所有讲师列表")
    public R findAllTeacher(){

        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);

    }


    //逻辑删除老师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){


        boolean flag=eduTeacherService.removeById(id);
        if (flag)
            return R.ok();
        else
            return R.error();

    }

    //分页查询讲师的方法
    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @PathVariable long current,
            @PathVariable long limit){


        Page<EduTeacher> page=new Page<>(current,limit);
        //调用方法的时候 底层封装，把分页的所有数据封装到pageTeacher对象里面去
        eduTeacherService.page(page,null);

        long total = page.getTotal();//总记录数
        List<EduTeacher> records = page.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //条件查询带分页的方法
    @ApiOperation("条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageQuery(@PathVariable long current,
                       @PathVariable long limit,
                       @RequestBody(required = false) TeacherQuery teacherQuery){
        //使用@RequestBody的话 那么前端会把json数据封装到这个类上 同时必须使用PostMapping提交才行

        //调用page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);

        //调用方法实现条件查询分页
        eduTeacherService.pageQuery(pageTeacher,teacherQuery);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);


    }


    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher){

        boolean save = eduTeacherService.save(teacher);

        if (save)
            return R.ok();
        return R.error();
    }


    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){




        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    @ApiOperation("根据id更新讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher teacher){

        boolean update = eduTeacherService.updateById(teacher);
        if (update)
            return R.ok();
        return R.error();
    }


}


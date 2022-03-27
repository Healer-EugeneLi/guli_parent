package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@Api("课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation("添加课程分类，Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){

        subjectService.addSubject(file,subjectService);
        return R.ok();
    }

    @ApiOperation("获取课程分类列表数据 树形")
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        //返回的列表里存放的是一级分类就行 因为一级中包含了二级分类的信息
        List<OneSubject> list= subjectService.getAllOneTwoSubject();
        return R.ok().data("item",list);
    }





}


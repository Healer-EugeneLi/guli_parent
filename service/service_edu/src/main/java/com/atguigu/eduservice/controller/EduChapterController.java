package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;


    @ApiOperation("获取课程大纲")
    @GetMapping("getChapter/{courseId}")
    private R getChapter(@PathVariable String courseId){

        List<ChapterVo> list=chapterService.getChapterByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    @ApiOperation("添加章节")
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){

        boolean save = chapterService.save(eduChapter);
        if (save==false)
            throw new GuliException(20001,"添加章节失败");
        return R.ok().message("添加章节成功");
    }

    @ApiOperation("根据章节id查询章节信息")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){

        EduChapter eduChapter = chapterService.getById(chapterId);
        if (eduChapter==null)
            throw new GuliException(20001,"获取章节信息失败");
        return R.ok().data("chapter",eduChapter);
    }

    @ApiOperation("更新章节信息")
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){

        boolean update = chapterService.updateById(chapter);
        if (update==false)
            throw new GuliException(20001,"更新章节信息失败");
        return R.ok().message("更新章节信息成功");

    }

    @ApiOperation("删除章节信息")
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){

        boolean res=chapterService.deleteChapter(chapterId);
        if (res==true)
            return R.ok().message("删除章节信息成功");
        return R.error().message("删除章节信息失败");
    }
}


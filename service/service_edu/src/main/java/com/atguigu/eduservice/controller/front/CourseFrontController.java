package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CourseFrontController
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/8
 * @Time: 16:12
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation("条件查询带分页查询课程")
    @PostMapping("getCourseFrontList/{page}/{limit}")
    public R getCourseFrontList(@PathVariable long page,
                                @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){

        Page<EduCourse> coursePage=new Page<>(page,limit);
        Map<String,Object> map= courseService.getCourseFrontList(coursePage,courseFrontVo);
        return R.ok().data("map",map);
    }

    @ApiOperation("根据courseId获取课程详情")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){


        //1.获取前端显示的对应的课程详情包装信息
        CourseWebVo courseWebVo= courseService.getFrontCourseDetail(courseId);


        //2.获取对应的小节章节
        List<ChapterVo> chapterVoList = chapterService.getChapterByCourseId(courseId);

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVoList",chapterVoList);
    }
}

package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    //课程简介的service注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    //小节的注入
    @Autowired
    private EduVideoService videoService;

    //章节的注入
    @Autowired
    private EduChapterService chapterService;

    /**
     * 添加课程信息
     *
     * @param courseInfo
     */
    @Override
    public String  addCourse(CourseInfo courseInfo) {

        //往课程表里面添加课程信息

        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0)
            throw new GuliException(20001,"添加课程信息失败");

        //添加成功之后获取课程表id
        String courseId = eduCourse.getId();

        //往课程简介表里面添加课程的简介信息
        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setId(courseId);
        courseDescription.setDescription(courseInfo.getDescription());
        boolean save = courseDescriptionService.save(courseDescription);
        if (save==false)
            throw new GuliException(20001,"添加课程简介信息失败");
        return courseId;

    }

    /**
     * 根据课程id获取封装的课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseInfo getCourseInfoByCourseId(String courseId) {

        CourseInfo courseInfo=new CourseInfo();


        //获取课程信息
        EduCourse eduCourse = baseMapper.selectById(courseId);

        if (eduCourse==null)
            throw new GuliException(20001,"获取课程信息失败");
        BeanUtils.copyProperties(eduCourse,courseInfo);

        //获取课程简介信息
        EduCourseDescription eduCourseDescription = courseDescriptionService.getById(eduCourse.getId());
        if (eduCourseDescription==null)
            throw new GuliException(20001,"获取简介信息失败");
        courseInfo.setDescription(eduCourseDescription.getDescription());

        return courseInfo;
    }

    /**
     * 根据课程id修改课程信息
     *
     * @param courseInfo
     */
    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {

        //先修改课程实体信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int res = baseMapper.updateById(eduCourse);
        if (res<=0)
            throw new GuliException(20001,"修改课程信息失败");

        //再修改简介信息
        EduCourseDescription description=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo,description);
        boolean flag = courseDescriptionService.updateById(description);
        if (flag==false)
            throw new GuliException(20001,"修改课程简介失败");

    }

    /**
     * 根据课程id获取课程发布时显示的课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {

        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        if (publishCourseInfo==null)
            throw new GuliException(20001,"获取课程发布信息失败");
        return publishCourseInfo;
    }

    /**
     * 分页带查询所有课程
     *
     * @param pageCourse
     * @param courseQuery
     */
    @Override
    public void pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery) {

        //先查询所有课程
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        //如果查询的条件为空 那么等同于进行全部查询
        if (courseQuery==null){
            baseMapper.selectPage(pageCourse,wrapper);
            return;
        }

        String title = courseQuery.getTitle();//课程名
        String teacherId = courseQuery.getTeacherId();//教师id
        String subjectParentId = courseQuery.getSubjectParentId();//一级分类id
        String subjectId = courseQuery.getSubjectId();//二级分类id

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }

        if (!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }

        baseMapper.selectPage(pageCourse,wrapper);

    }

    /**
     * 删除课程 先删除video中的记录 再删除chapter中的记录 最后删除course中的记录
     *
     * @param courseId
     * @return
     */
    @Override
    public boolean removeCourseById(String courseId) {

        //小节中删除
        boolean videoRes=videoService.removeVideoByCourseId(courseId);

        //在章节中进行删除
        boolean chapterRes=chapterService.removeChapterByCourseId(courseId);

        Integer delete = baseMapper.deleteById(courseId);
        return delete!=null&&delete>0;
    }


}

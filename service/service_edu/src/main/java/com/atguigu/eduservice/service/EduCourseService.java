package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     * @param courseInfo
     */
    String  addCourse(CourseInfo courseInfo);

    /**
     * 根据课程id获取封装的课程信息
     * @param courseId
     * @return
     */
    CourseInfo getCourseInfoByCourseId(String courseId);

    /**
     * 根据课程id修改课程信息
     * @param courseInfo
     */
    void updateCourseInfo(CourseInfo courseInfo);

    /**
     * 根据课程id获取课程发布时显示的课程信息
     * @param courseId
     * @return
     */
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 分页带查询所有课程
     * @param pageCourse
     * @param courseQuery
     */
    void pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery);

    /**
     * 删除课程 先删除video中的记录 再删除chapter中的记录 最后删除course中的记录
     * @param courseId
     * @return
     */
    boolean removeCourseById(String courseId);
}
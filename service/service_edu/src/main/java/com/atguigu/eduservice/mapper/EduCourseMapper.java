package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程id得到课程发布时显示的信息
     * @param curseId
     * @return
     */
    public CoursePublishVo getPublishCourseInfo(String curseId);
}

package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CoursePublishVo
 * @Description: 课程发布的时候需要显示的信息封装
 * @Author EugeneLi
 * @Date: 2022/3/28
 * @Time: 21:57
 */
@Data
public class CoursePublishVo implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;//课程id
    private String title;//课程名
    private String cover;//课程封面
    private Integer lessonNum;//课程数
    private String teacherName;//老师名字
    private String subjectLevelOne;//一级分类
    private String subjectLevelTwo;//二级分类
    private String price;//课程价格

}

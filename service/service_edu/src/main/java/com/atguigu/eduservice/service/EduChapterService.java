package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 通过courseId获取章节 小节信息
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterByCourseId(String courseId);
}

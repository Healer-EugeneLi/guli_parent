package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 从Excel中导入课程分类的数据
     * @param file
     * @param subjectService
     */
    void addSubject(MultipartFile file,EduSubjectService subjectService);
}

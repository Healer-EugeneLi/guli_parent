package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 通过courseId获取章节 小节信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterByCourseId(String courseId) {

        //1.先根据courseId 获取对应的Chapter
        QueryWrapper<EduChapter> eduChapterQueryWrapper=new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterQueryWrapper);

        //2.再获取对应的小节信息
        QueryWrapper<EduVideo> eduVideoQueryWrapper=new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(eduVideoQueryWrapper);

        //最终的返回结果
        List<ChapterVo> res=new ArrayList<>();

        //进行封装
        for (int i=0;i<eduChapters.size();i++){

            ChapterVo chapterVo=new ChapterVo();//目标
            EduChapter eduChapter = eduChapters.get(i);//源数据
            BeanUtils.copyProperties(eduChapter,chapterVo);//拷贝

            //开始封装对应的小节信息
            String chapterId = eduChapter.getId();//此时的章节id

            //通过章节id找到对应的小节id
            List<VideoVo> children=new ArrayList<>();
            for (int j=0;j<eduVideos.size();j++){

                EduVideo eduVideo = eduVideos.get(j);
                if (eduVideo.getChapterId().equals(chapterId)){

                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);//拷贝数据
                    children.add(videoVo);//加入章节列表
                }
            }
            chapterVo.setChildren(children);//设置章节的小节 孩子信息
            res.add(chapterVo);
        }

        return res;
    }

    /**
     * 根据章节id删除章节信息
     *
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {

        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count>0){
            //说明有章节中有小节
            throw new GuliException(20001,"删除失败，章节中存在小节");
        }else {

            int delete = baseMapper.deleteById(chapterId);
            return delete>0;
        }

    }

    /**
     * 根据courseId删除章节信息
     *
     * @param courseId
     * @return
     */
    @Override
    public boolean removeChapterByCourseId(String courseId) {

        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        Integer delete = baseMapper.delete(wrapper);
        return delete!=null&&delete>0;
    }
}

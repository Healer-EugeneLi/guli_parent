package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;//为了进行远程删除批量视频进行注册

    /**
     * 根据courseId删除小节
     *
     * @param courseId
     */
    @Override
    public boolean removeVideoByCourseId(String courseId) {

        //根据courseId 得到所有的小节videoId
        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");//只需要查询视频id就行

        List<EduVideo> eduVideos = baseMapper.selectList(queryWrapper);
        //存放所有的视频id
        List<String> videoIdsList=new ArrayList<>();
        for (EduVideo video:eduVideos){

            //视频id不为空
            String videoId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoId)){
                videoIdsList.add(videoId);
            }
        }
        //最后该课程中存在着视频 则进行视频的批量删除
        if (videoIdsList.size()>0){
            System.out.println("批量删除list:"+videoIdsList.toArray());
            vodClient.deleteBatch(videoIdsList);
        }

        //进行小节的删除
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);

        Integer delete = baseMapper.delete(wrapper);
        return  delete!=null&&delete>0;
    }
}

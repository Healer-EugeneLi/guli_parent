package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: VodService
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/3
 * @Time: 21:25
 */
public interface VodService {

    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    String uploadAlyVideo(MultipartFile file);

    /**
     * 删除video
     * @param videoId
     */
    void deleteVideoByVideoId(String videoId);

    /**
     * 根据videoId 列表进行批量删除
     * @param videoList
     */
    void deleteBatch(List<String> videoList);
}

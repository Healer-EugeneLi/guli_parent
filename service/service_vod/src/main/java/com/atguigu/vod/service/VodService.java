package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

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
}

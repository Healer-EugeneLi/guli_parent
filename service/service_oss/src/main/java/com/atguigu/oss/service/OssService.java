package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: OssService
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 15:41
 */

public interface OssService {

    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}

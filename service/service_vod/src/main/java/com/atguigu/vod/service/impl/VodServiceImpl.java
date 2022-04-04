package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: VodServiceImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/3
 * @Time: 21:26
 */
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @Override
    public String uploadAlyVideo(MultipartFile file) {

        String videoId=null;
        try {
            InputStream inputStream = null;
            inputStream = file.getInputStream();//文件的输入流
            String originalFilename = file.getOriginalFilename();//原始的文件名称
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));//上传之后显示的名称

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader=new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因


            if (response.isSuccess()){
                videoId=response.getVideoId();
            }else {
                String errorMessage="阿里云上传失误："+"code:"+response.getCode()+",message:"+response.getMessage();
                throw new GuliException(20001,errorMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return videoId;
    }
}

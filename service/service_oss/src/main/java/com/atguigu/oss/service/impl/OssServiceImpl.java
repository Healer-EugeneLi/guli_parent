package com.atguigu.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: OssServiceImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 15:42
 */
@Service
public class OssServiceImpl implements OssService {


    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        // 获取工具类中连接阿里云的相关信息
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId =ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret =ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        // 1.文件的原始名称
        String fileName = file.getOriginalFilename();
        System.out.println("fileName 文件的原始名称"+fileName);
        //在原始的文件名称上加上一个随机的唯一值 把生成的- 替换成空格
        String uuid = UUID.randomUUID().toString().replace("-","");
        //e.g  uuid+name.jpg
        fileName=uuid+fileName;

        //2.把文件安装日期进行分类 2022/03/25
        String datePath = new DateTime().toString("yyyy/MM/dd");

        //拼接
        fileName=datePath+"/"+fileName;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, inputStream);
        }catch (IOException E){
            System.out.println("file error");
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }

            //把上传之后的文件路径返回
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }


    }
}

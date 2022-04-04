package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @ClassName: TestVod
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/2
 * @Time: 11:30
 */
public class TestVod {

    /**
     * 根据视频id获取视频播放地址
     */
    public static void getVideoUrl(){
        String accessKid="LTAI5tHasvN5cYCEsh1uYfpe";
        String accessKeySecret="wp7N0o4iYa0PZ4JPG2pcRs968S27sZ";

        try {
            //1.创建初始化话对象
            DefaultAcsClient client=InitObject.initVodClient(accessKid,accessKeySecret);

            //2.创建获取视频地址的request 和response
            GetPlayInfoRequest request=new GetPlayInfoRequest();
            GetPlayInfoResponse response=new GetPlayInfoResponse();

            //3.在request中设置视频id
            request.setVideoId("d41b58e26c86436daf8c034a34f0bf2d");

            //4.调用client的请求 获取response
            response=client.getAcsResponse(request);


            //5.通过response获取视频列表
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();

            //6.得到播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


    /**
     * 得到播放凭证
     */
    public static void getPlayAuth() {
        String accessKid="LTAI5tHasvN5cYCEsh1uYfpe";
        String accessKeySecret="wp7N0o4iYa0PZ4JPG2pcRs968S27sZ";


        try {
            //1.创建初始化话对象
            DefaultAcsClient client=InitObject.initVodClient(accessKid,accessKeySecret);

            //2.创建获取视频地址的request 和response
            GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response=new GetVideoPlayAuthResponse();

            //3.在request中设置视频id
            request.setVideoId("d41b58e26c86436daf8c034a34f0bf2d");

            //4.调用client的请求 获取response
            response=client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            System.out.println("playAuth:"+playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }

    public static void uploadVideo(){

        String accessKeyId="LTAI5tHasvN5cYCEsh1uYfpe";
        String accessKeySecret="wp7N0o4iYa0PZ4JPG2pcRs968S27sZ";
        //1.音视频上传-本地文件上传
        //视频标题(必选)
        String title = "6 - What If I Want to Move Fasterk - upload by sdk";
        //本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选)
        //文件名必须包含扩展名
        String fileName = "D:/Java/资料/项目资料/谷粒学院/项目资料/1-阿里云上传测试视频/6 - What If I Want to Move Faster.mp4";
        //本地文件上传
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
        request.setPartSize(1 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
    /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }


    }
    public static void main(String[] args) {

//        TestVod.getVideoUrl();

//        TestVod.getPlayAuth();

        TestVod.uploadVideo();


    }
}

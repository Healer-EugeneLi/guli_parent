package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @InterfaceName: VodClient
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/5
 * @Time: 14:50
 */
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)//熔断器
public interface VodClient {

    //根据videoId删除视频
    @DeleteMapping("/eduvod/video/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable("videoId") String videoId);

    //批量删除视频
    @DeleteMapping("/eduvod/video/deleteBatchVideo")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoList);
}

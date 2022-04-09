package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: VodFileDegradeFeignClient
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/5
 * @Time: 20:07
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R deleteVideo(String videoId) {
        return R.error().message("删除视频 time out");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("删除批量视频 time out");
    }
}

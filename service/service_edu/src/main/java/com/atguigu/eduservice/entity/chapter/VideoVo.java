package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VideoVo
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/27
 * @Time: 19:48
 */

@Data
public class VideoVo {

    private String id;//小节id

    private String title;//小节名称

    private String videoSourceId;//视频id

//    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Boolean isFree;

}

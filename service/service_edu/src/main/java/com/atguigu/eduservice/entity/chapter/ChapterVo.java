package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ChapterVo
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/27
 * @Time: 19:48
 */

@Data
public class ChapterVo {

    private String id;//章节id

    private String title;//章节名称

    //章节对应的小节
    private List<VideoVo> children=new ArrayList<>();
}

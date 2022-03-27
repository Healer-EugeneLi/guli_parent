package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OneSubject
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/26
 * @Time: 19:15
 *
 * 针对返回数据创建对应的实体类 两个实体类 一个一级分类 和一个二级分类
 * 然后使用一个List来表达一个一级分类与多个二级分类的对应关系
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    //一级分类对应多个二级分离
    private List<TwoSubject> children=new ArrayList<>();
}

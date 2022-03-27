package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.ExcelSubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listerner.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 从Excel中导入课程分类的数据
     *
     * @param file
     * @param subjectService
     */
    @Override
    public void addSubject(MultipartFile file,EduSubjectService subjectService) {


        try {
            //文件读入流
            InputStream inputStream=file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取课程列表的信息
     *
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        List<OneSubject> res=new ArrayList<>();

        //1.先查询所有一级分类 parent_id=0
        QueryWrapper<EduSubject> oneWrapper=new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> oneEduSubjects= baseMapper.selectList(oneWrapper);

        //2.查询所有的二级分类 parent_id!=0
        QueryWrapper<EduSubject> twoWrapper=new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<EduSubject> twoEduSubjects= baseMapper.selectList(twoWrapper);

        //3.将一级分类的数据封装到最后的结果列表
        for (int i=0;i<oneEduSubjects.size();i++){

            OneSubject oneSubject=new OneSubject();
            EduSubject eduSubject = oneEduSubjects.get(i);//将部分信息复制到结果集合要存放的项中
            BeanUtils.copyProperties(eduSubject,oneSubject);

            String id = oneSubject.getId();//一级分类的id

            //4.将二级分类的数据封装到最后的结果列表中对应的一级分类中去
            List<TwoSubject> children=new ArrayList<>();
            //遍历查到的二级分类数据的列表 根据对应的parent_id 来找到匹配的一级分类
            for (int j=0;j<twoEduSubjects.size();j++){
                EduSubject eduSubjectTwo = twoEduSubjects.get(j);
                if (eduSubjectTwo.getParentId().equals(id)){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(eduSubjectTwo,twoSubject);
                    children.add(twoSubject);
                }
            }
            oneSubject.setChildren(children);
            res.add(oneSubject);
        }

        //5.返回完整的结果列表
        return res;
    }
}

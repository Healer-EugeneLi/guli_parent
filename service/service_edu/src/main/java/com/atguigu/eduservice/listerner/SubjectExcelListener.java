package com.atguigu.eduservice.listerner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.ExcelSubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @ClassName: SubjectExcelListener
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/26
 * @Time: 0:34
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {


    //因为SubjectExcelListener是不能交给spring进行管理的 需要自己new 不能注入其他对象 不能实现数据库操作
    //所以是没有办法使用Autowired进行注入的  所以只能使用传入参数进行操作

    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取Excel的内容 一行一行地进行读取
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {

        if (excelSubjectData==null){
            throw  new GuliException(20001,"添加失败");
        }

        //对一级分类进行判断
        EduSubject existOneSubject = this.isExistOneSubject(subjectService, excelSubjectData.getOneSubjectName());
        if (existOneSubject==null){
            //如果一级课程为空 可以加入数据库
            existOneSubject=new EduSubject();
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        //获取一级分类的id 来进行判断是否存在二级分类
        String pid=existOneSubject.getId();

        EduSubject existTwoSubject = this.isExistTwoSubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);

        if (existTwoSubject==null){
            //二级课程为空 可以加入数据库
            existTwoSubject=new EduSubject();
            existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }

    }


    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }





    /**
     * 判断一级分类是否重复
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject isExistOneSubject(EduSubjectService subjectService,String name){

        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }


    /**
     *
     * @param subjectService
     * @param name
     * @param pid
     * @return
     */
    public EduSubject isExistTwoSubject(EduSubjectService subjectService,String name,String pid){

        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();

        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
}

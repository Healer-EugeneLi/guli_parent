package com.atguigu.demo;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName: ExcelListener
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 23:58
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {


    //一行一行地读取Excel的内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
    }
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

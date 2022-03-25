package com.atguigu.demo;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestEasyExcel
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 23:51
 */
public class TestEasyExcel {

    public static void main(String[] args) {

//
//
//        //将数据写入到这个表格
//        String fileName="./demo.xlsx";
//
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表1").doWrite(data());


        //实现读操作
        String fileName="./demo.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();


    }

    public static List<DemoData> data(){

        List<DemoData> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setName("student-"+i);
            list.add(data);
        }
        return list;
    }

}

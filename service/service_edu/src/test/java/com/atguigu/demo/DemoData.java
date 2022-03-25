package com.atguigu.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: DemoData
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 23:49
 */

//设置表达和添加的数据
@Data
public class DemoData {

    //设置表头名称
    @ExcelProperty("学生编号")
    private int sno;

    @ExcelProperty("学生姓名")
    private String name;

}

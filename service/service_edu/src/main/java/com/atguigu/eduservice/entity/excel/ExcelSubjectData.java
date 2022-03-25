package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: ExcelSubjectData
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/26
 * @Time: 0:31
 */

@Data
public class ExcelSubjectData {

    @ExcelProperty(index = 0)
    public String oneSubjectName;

    @ExcelProperty(index = 1)
    public String twoSubjectName;
}

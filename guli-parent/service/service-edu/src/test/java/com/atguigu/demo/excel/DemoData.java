package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * DemoData
 *
 * @author fj
 * @date 2022/10/31 23:10
 */
@Data
public class DemoData {
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String sname;

}

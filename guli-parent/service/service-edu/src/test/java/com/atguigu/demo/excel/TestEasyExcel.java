package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * TestExcelDemo
 *
 * @author fj
 * @date 2022/10/31 23:28
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
        //1. 设置写入文件夹地址和Excel文件名称
        String fileName="D:\\write.xlsx";
        //2. 调用easyExcel里面的方法实现写操作
        /**
         * 参数1 文件路径名称
         * 参数2 实体类
         */
        //EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
        //读
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    //创建一个方法，返回一个list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张"+i);
            list.add(data);
        }
        return list;
    }
}

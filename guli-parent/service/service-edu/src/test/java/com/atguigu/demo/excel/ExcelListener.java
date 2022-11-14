package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * ExcelListener
 *
 * @author fj
 * @date 2022/10/31 23:46
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //逐行读取Excel文件
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("demoData = " + demoData);
    }
    //读取文件头的内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("headMap = " + headMap);
    }

    //读取完成后do
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

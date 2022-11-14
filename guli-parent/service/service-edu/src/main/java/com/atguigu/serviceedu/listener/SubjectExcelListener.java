package com.atguigu.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectData;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * SubjectExcelListener
 *
 * @author fj
 * @date 2022/11/1 9:16
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener不能交给spring管理，需要自己new，所有其他对象也不能注入进来
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //读取Excel文件内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null) throw new GuliException(20001,"文件数据为空");
        //一行一行读取，第一个值一级分类，第二个值，二级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {
            //表里没有相同的一级分类
            existOneSubject=new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }
        String pid=existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoEduSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject=new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }

    }
    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        return eduSubjectService.getOne(wrapper);
    }
    //判断二级分类不能重复
    private EduSubject existTwoEduSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return eduSubjectService.getOne(wrapper);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

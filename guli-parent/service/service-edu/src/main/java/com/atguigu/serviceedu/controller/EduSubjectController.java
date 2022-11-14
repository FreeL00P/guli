package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.subject.OneSubject;
import com.atguigu.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    //添加课程分类 从Excel读取
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //得到上传过来的Excel文件
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }
    //获取所有课程信息
    @Cacheable(key = "'eduSubject'",value = "getAllSubject")
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> list = eduSubjectService.getAllSubject();
        return R.ok().data("list",list);
    }

}


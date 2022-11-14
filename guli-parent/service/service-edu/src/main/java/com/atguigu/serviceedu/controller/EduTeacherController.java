package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.vo.TeacherQuery;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduTeacherService;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-10-29
 */
@CrossOrigin//解决跨域
@RestController
@RequestMapping("/eduservice/teacher")

public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //查询讲师表所有数据
    @ApiOperation(value = "查询讲师表所有数据")
    @Cacheable(key = "'eduTeacher'",value = "findAll")
    @GetMapping("/findAll")
    public R findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    //根据id删除讲师信息
    @ApiOperation(value = "根据id删除讲师信息")
    @CacheEvict(value = "eduTeacher",allEntries = true)
    @DeleteMapping("{id}")
    public R removeTeacherById(@PathVariable("id") String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) return R.ok();
        else return R.error();
    }

    @ApiOperation(value = "分页讲师列表（后台）")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R page(@ApiParam(name = "page", value = "当前页码", required = true)
                  @PathVariable Long page,
                  @ApiParam(name = "limit", value = "每页记录数", required = true)
                  @PathVariable Long limit,
                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("分页讲师列表（前台）")
    @GetMapping("{page}/{limit}")
    public R pageList(@PathVariable("page") Long page,@PathVariable("limit") Long limit){
        Page<EduTeacher> pageParams=new Page<>(page, limit);
        Map<String, Object> teacherPageList = teacherService.pageListWeb(pageParams);
        return R.ok().data("teacherPageList",teacherPageList);
    }

    @ApiOperation(value = "添加讲师信息")
    @CacheEvict(value = "eduTeacher",allEntries = true)
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean ret = teacherService.save(eduTeacher);
        if (ret) return R.ok();
        else return R.error();
    }
    @ApiOperation(value = "根据id查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable("id") String id){
        //查询讲师信息
        EduTeacher teacher = teacherService.getById(id);
        //查询讲师所教的课程信息
        List<EduCourse> courseList = courseService.selectByTeacherId(id);
        if (teacher!=null) return R.ok().data("teacher",teacher).data("courseList",courseList);
        else  return R.error();
    }
    @ApiOperation(value = "修改讲师信息")
    @CacheEvict(value = "eduTeacher",allEntries = true)
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean ret = teacherService.updateById(eduTeacher);
        if (ret) return R.ok();
        else return R.error();
    }
}


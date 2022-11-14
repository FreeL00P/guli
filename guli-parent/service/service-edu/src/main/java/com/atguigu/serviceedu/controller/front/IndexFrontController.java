package com.atguigu.serviceedu.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * IndexFrontController
 *
 * @author fj
 * @date 2022/11/6 16:18
 */
@Api("前台Index")
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation("查询前8门课程 前4条名师")
    @GetMapping("index")
    public R index(){
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> list = courseService.list(wrapper);

        QueryWrapper<EduTeacher> wrapperT = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> listT = teacherService.list(wrapperT);
        return R.ok().data("eduList",list).data("teacherList",listT);
    }

}

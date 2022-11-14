package com.atguigu.serviceedu.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TeacherFrontController
 *
 * @author fj
 * @date 2022/11/10 13:44
 */
@Api("讲师分页查询 前台")
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService ;

    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable("page") long page,@PathVariable("limit") long limit) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam,null);
        List<EduTeacher> teacherList = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("items", teacherList);
    }

}

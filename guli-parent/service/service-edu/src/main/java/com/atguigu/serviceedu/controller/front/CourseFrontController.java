package com.atguigu.serviceedu.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.serviceedu.client.OrderClient;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.chapter.ChapterVo;
import com.atguigu.serviceedu.entity.vo.CourseQuery;
import com.atguigu.serviceedu.entity.vo.CourseWebVo;
import com.atguigu.serviceedu.entity.vo.TeacherQuery;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CourseFrontController
 *
 * @author fj
 * @date 2022/11/10 13:57
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable("page") long page,
                                 @PathVariable("limit") long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam,courseQuery);
        List<EduCourse> courseList = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("items", courseList);
    }

    @GetMapping("getFrontCourseInfo/{id}")
    public R getCourseInfo(@PathVariable("id") String id, HttpServletRequest request){
        //课程浏览数加一
        courseService.updatePageViewCount(id);
        //查询课程信息
        CourseWebVo course = courseService.getCourseInfoById(id);
        //查询课程下的章节小节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideo(id);
        //判断用户是否已经购买课程
        String memberId= JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = orderClient.isBuyCourse(memberId, id);
        return R.ok().data("courseWebVo",course).data("chapterVideoList",chapterVoList).data("isBuy",buyCourse);
    }
    //根据id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id){
        CourseWebVo courseWebVo = courseService.getCourseInfoById(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo, courseWebVoOrder);
        return courseWebVoOrder;
    }
}

package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.entity.vo.CourseInfo;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-02
 */
@ApiModel("课程信息管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private VodClient vodClient;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @ApiOperation(value = "添加课程信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo){
        String id = eduCourseService.saveCourseInfo(courseInfo);
        //返回添加之后课程id，为了后面添加大纲使用
        return R.ok().data("courseId",id);
    }

    @ApiOperation("获取课程列表")
    @Cacheable(key = "'eduCourse'",value = "getCourseInfo")
    @GetMapping
    public R getCourseInfo() {
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation("课程确让信息显示")
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable("courseId") String courseId){
        CoursePublishVo publishCourseVo = eduCourseService.getPublishCourseInfo(courseId);
        return R.ok().data("publishCourse",publishCourseVo);
    }

    @ApiOperation("课程最终发布确认")
    @PostMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable("courseId") String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation("删除课程信息")
    @CacheEvict(value = "eduCourse",allEntries = true)
    @DeleteMapping("removeCourseById/{courseId}")
    @Transactional
    public R deleteCourse(@PathVariable("courseId") String courseId){
        //删除课程小节信息
        eduVideoService.removeVideoByCourseId(courseId);
        //删除课程小节视频
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        //wrapper.select("video_source_id");
        List<EduVideo> videoList = eduVideoService.list(wrapper);
        List<String> videoIds=new ArrayList<>();
        for (EduVideo video : videoList) {
            String id=video.getVideoSourceId();
            if (!StringUtils.isEmpty(id)){
                videoIds.add(id);
            }
        }
        vodClient.deleteBatch(videoIds);
        //删除课程章节信息
        eduChapterService.removeChapterByCourseId(courseId);
        //删除课程描述信息
        eduCourseDescriptionService.removeDescByCourseId(courseId);
        //删除课程信息
        boolean ret  = eduCourseService.removeById(courseId);
        if (ret) return R.ok();
        else return R.error();
    }
}


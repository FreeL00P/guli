package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.vo.CourseInfo;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.entity.vo.CourseQuery;
import com.atguigu.serviceedu.entity.vo.CourseWebVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-02
 */
public interface EduCourseService extends IService<EduCourse> {
    String saveCourseInfo(CourseInfo courseInfo);

    /**
     * 最终发布课程 展示课程信息
     * @param courseId
     * @return
     */
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 课程信息分页 条件查询
     * @param pageParam
     * @param courseQuery
     */
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    /**
     * 获取课程信息 多表查询 涉及mapper
     * @param id
     * @return
     */
    CourseWebVo getCourseInfoById(String id);
    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);

    /**
     * 根据讲师id查询所教的课程列表
     */
    List<EduCourse> selectByTeacherId(String teacherId);


}

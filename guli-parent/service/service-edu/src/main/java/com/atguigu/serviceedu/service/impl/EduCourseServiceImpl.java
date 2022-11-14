package com.atguigu.serviceedu.service.impl;

import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.atguigu.serviceedu.entity.vo.CourseInfo;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.entity.vo.CourseQuery;
import com.atguigu.serviceedu.entity.vo.CourseWebVo;
import com.atguigu.serviceedu.mapper.EduCourseMapper;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper,EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Override
    @Transactional
    public String saveCourseInfo(CourseInfo courseInfo) {
        //添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        boolean ret = this.save(eduCourse);
        if (!ret) {
            throw new GuliException(20001,"课程信息添加失败");
        }
        //获取添加后的课程id
        String cid=eduCourse.getId();
        //添加课程描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        //将课程id设置到描述表的id 关联两表
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription(courseInfo.getDescription());
        boolean res = courseDescriptionService.save(eduCourseDescription);
        if (!res) {
            throw new GuliException(20001,"课程描述信息添加失败");
        }
        return cid;
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (courseQuery==null) {
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }
        Integer buyCountSort = courseQuery.getBuyCountSort();
        Integer priceSort = courseQuery.getPriceSort();
        Integer gmtCreateSort = courseQuery.getGmtCreateSort();
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        if (!StringUtils.isEmpty(buyCountSort)){
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(priceSort)){
            queryWrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)){
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        baseMapper.selectPage(pageParam, queryWrapper);

    }

    @Override
    public CourseWebVo getCourseInfoById(String id) {

        return baseMapper.getCourseInfo(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount()+1);
        baseMapper.updateById(course);
    }

    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        wrapper.orderByDesc("gmt_modified");
        List<EduCourse> courseList = baseMapper.selectList(wrapper);
        return courseList;
    }
}

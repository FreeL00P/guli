package com.atguigu.serviceedu.service.impl;

import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.atguigu.serviceedu.mapper.EduCourseDescriptionMapper;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeDescByCourseId(String courseId) {
        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        //课程id与描述id一致
        wrapper.eq("id",courseId);
        this.remove(wrapper);
    }
}

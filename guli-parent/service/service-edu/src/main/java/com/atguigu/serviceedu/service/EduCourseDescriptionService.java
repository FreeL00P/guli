package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeDescByCourseId(String courseId);
}

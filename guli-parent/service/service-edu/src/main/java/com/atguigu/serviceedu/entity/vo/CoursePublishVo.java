package com.atguigu.serviceedu.entity.vo;

import lombok.Data;

/**
 * CoursePublishVo
 *
 * @author fj
 * @date 2022/11/4 10:43
 */

@Data
public class CoursePublishVo {

    private String id;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    private String price;
}


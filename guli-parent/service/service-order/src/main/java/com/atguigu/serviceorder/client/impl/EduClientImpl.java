package com.atguigu.serviceorder.client.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.serviceorder.client.EduClient;
import org.springframework.stereotype.Component;

/**
 * EduClientImpl
 *
 * @author fj
 * @date 2022/11/12 14:32
 */
@Component
public class EduClientImpl implements EduClient {
    @Override
    public CourseWebVoOrder getCourseWebVoOrder(String courseId) {
        return null;
    }
}

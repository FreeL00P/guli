package com.atguigu.serviceorder.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.serviceorder.client.impl.EduClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * EduClient
 *
 * @author fj
 * @date 2022/11/12 14:31
 */
@Component
@FeignClient(name="service-edu",fallback = EduClientImpl.class)
public interface EduClient {

    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    CourseWebVoOrder getCourseWebVoOrder(@PathVariable("id") String courseId);


}

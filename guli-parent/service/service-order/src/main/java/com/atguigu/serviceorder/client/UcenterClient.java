package com.atguigu.serviceorder.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UCenterMemberOrder;
import com.atguigu.serviceorder.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * UcenterClient
 *
 * @author fj
 * @date 2022/11/12 14:30
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {


    @PostMapping("/educenter/member/getUserInfoOrder/{memberId}")
    UCenterMemberOrder getUcenterPay(@PathVariable("memberId") String
                                             memberId);



}

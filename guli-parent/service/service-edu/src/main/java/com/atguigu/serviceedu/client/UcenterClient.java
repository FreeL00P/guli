package com.atguigu.serviceedu.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UCenterMemberOrder;
import com.atguigu.serviceedu.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * UcenterClient
 *
 * @author fj
 * @date 2022/11/11 19:26
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    @PostMapping("/educenter/member/getUserInfoOrder/{memberId}")
    UCenterMemberOrder getUcenterPay(@PathVariable("memberId") String
                                             memberId);


}

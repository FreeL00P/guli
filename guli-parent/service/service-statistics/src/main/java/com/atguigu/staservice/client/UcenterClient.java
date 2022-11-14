package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.staservice.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * UcenterClient
 *
 * @author fj
 * @date 2022/11/13 14:50
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @GetMapping(value = "/educenter/member/countregister/{day}")
    R registerCount(@PathVariable("day") String day);
}

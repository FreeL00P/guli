package com.atguigu.serviceedu.client;

import com.atguigu.serviceedu.client.impl.OrderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * OrderClient
 *
 * @author fj
 * @date 2022/11/13 13:16
 */
@Component
@FeignClient(value = "service-order", fallback = OrderClientImpl.class)
public interface OrderClient {
    @GetMapping("/eduorder/order/isBuyCourse/{memberid}/{id}")
    boolean isBuyCourse(@PathVariable("memberid") String memberid,
                               @PathVariable("id") String id);
}

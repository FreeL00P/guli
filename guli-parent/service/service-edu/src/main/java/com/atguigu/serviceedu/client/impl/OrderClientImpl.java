package com.atguigu.serviceedu.client.impl;

import com.atguigu.serviceedu.client.OrderClient;
import org.springframework.stereotype.Component;

/**
 * OrderClientImpl
 *
 * @author fj
 * @date 2022/11/13 13:16
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}

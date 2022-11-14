package com.atguigu.serviceorder.client.impl;

import com.atguigu.commonutils.ordervo.UCenterMemberOrder;
import com.atguigu.serviceorder.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * UcenterClientImpl
 *
 * @author fj
 * @date 2022/11/12 14:31
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UCenterMemberOrder getUcenterPay(String memberId) {
        return null;
    }
}

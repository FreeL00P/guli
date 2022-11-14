package com.atguigu.serviceedu.client.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UCenterMemberOrder;
import com.atguigu.serviceedu.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * UcenterClientImpl
 *
 * @author fj
 * @date 2022/11/11 19:29
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UCenterMemberOrder getUcenterPay(String memberId) {
        return null;
    }

}

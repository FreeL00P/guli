package com.atguigu.serviceorder.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UCenterMemberOrder;
import com.atguigu.serviceorder.client.EduClient;
import com.atguigu.serviceorder.client.UcenterClient;
import com.atguigu.serviceorder.entity.TOrder;
import com.atguigu.serviceorder.mapper.TOrderMapper;
import com.atguigu.serviceorder.service.TOrderService;
import com.atguigu.serviceorder.util.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ueditorClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        //远程调用课程服务,根据课程id获取课程信息
        CourseWebVoOrder course= eduClient.getCourseWebVoOrder(courseId);
        //远程调用用户服务，根据用户id获取用户信息
        UCenterMemberOrder ucenter = ueditorClient.getUcenterPay(memberId);
        //创建订单
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(course.getTitle());
        order.setCourseCover(course.getCover());
        order.setTeacherName("test");
        order.setTotalFee(course.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenter.getMobile());
        order.setNickname(ucenter.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);


        return order.getOrderNo();
    }
}

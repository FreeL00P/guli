package com.atguigu.serviceorder.service;

import com.atguigu.serviceorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberIdByJwtToken);
}

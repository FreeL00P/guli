package com.atguigu.serviceorder.service;

import com.atguigu.serviceorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
public interface TPayLogService extends IService<TPayLog> {

    Map<String,Object>  createNative(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}

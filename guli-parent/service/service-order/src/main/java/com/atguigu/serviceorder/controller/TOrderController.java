package com.atguigu.serviceorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.serviceorder.entity.TOrder;
import com.atguigu.serviceorder.service.TOrderService;
import com.atguigu.serviceorder.service.TPayLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
@Api("订单管理")
@RestController
@RequestMapping("/eduorder/order")
///eduorder/order/createOrder/1192252213659774977
@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private TPayLogService tPayLogService;

    @ApiOperation("创建订单")
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单，返回订单号
        String orderNo = tOrderService.createOrder(courseId, memberId);
        return R.ok().data("orderId",orderNo);
    }
    //根据订单id获取订单信息
    @ApiOperation("根据订单id获取订单信息")
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfoById(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = tOrderService.getOne(wrapper);
        return R.ok().data("item",order);
    }

    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberid)
                .eq("course_id", id)
                .eq("status", 1);
        int count = tOrderService.count(wrapper);
        return count > 0;
    }
}


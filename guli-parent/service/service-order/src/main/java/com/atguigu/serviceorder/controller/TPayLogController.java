package com.atguigu.serviceorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;

    //生成支付二维码接口
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        //返回信息，包含二维码地址，以及其他信息
        Map<String, Object> map=payLogService.createNative(orderNo);
        return R.ok();
    }
    //查询订单状态
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {
            return R.error().message("支付出错了");
        }
        if(map.get("trade_state").equals("success")){
            //向支付表中添加记录，并更新订单表的状态
            payLogService.updateOrderStatus(map);
            return R.ok();
        }
        return R.ok().message("支付中");
    }
}


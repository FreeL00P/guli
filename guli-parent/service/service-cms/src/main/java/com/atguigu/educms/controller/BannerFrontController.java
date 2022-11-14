package com.atguigu.educms.controller;

import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BannerFrontController
 *
 * @author fj
 * @date 2022/11/6 15:24
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation("获取首页banner")
    @GetMapping("getAllBanner")
    @Cacheable(value = "banner",key = "'seleteIndexList'")
    public R index(){
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        //根据id进行排序 获取前两条数据
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> list = bannerService.selectIndexList();
        return R.ok().data("bannerList",list);
    }
}

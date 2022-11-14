package com.atguigu.educms.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-06
 */
@Api("banner后台控制")
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation("分页查询banner")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit){
        Page<CrmBanner> bannerPage = new Page<CrmBanner>();
        IPage<CrmBanner> pageList = bannerService.page(bannerPage, null);
        return R.ok().data("items",pageList);
    }

    @ApiOperation("根据id查询banner")
    @GetMapping("get/{id}")
    public R getBanner(@PathVariable("id") Long id){
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item",banner);
    }

    @ApiOperation("添加banner")
    @CacheEvict(value = "banner",allEntries = true)
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation("更新banner")
    @CacheEvict(value = "banner",allEntries = true)
    @PutMapping("update")
    public R updateBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    @ApiOperation("删除banner")
    @CacheEvict(value = "banner",allEntries = true)
    @DeleteMapping("remove/{id}")
    public R deleteBanner(@PathVariable("id") Long id) {
        bannerService.removeById(id);
        return R.ok();
    }

}


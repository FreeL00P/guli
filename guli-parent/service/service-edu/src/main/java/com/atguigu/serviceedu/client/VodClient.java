package com.atguigu.serviceedu.client;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * VodClient
 *
 * @author fj
 * @date 2022/11/5 14:00
 */
@FeignClient(name="service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {
    //定义需要调用的方法路径
    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAliVideo/{id}")
    R deleteVideo(@PathVariable("id") String videoId);
    //定义删除多个视频的方法
    @DeleteMapping("/eduvod/video/delete-batch")
    R deleteBatch(@RequestParam("videoList")List<String> videoList);
}

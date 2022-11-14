package com.atguigu.serviceedu.client.impl;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * VodClientImpl
 *
 * @author fj
 * @date 2022/11/6 11:12
 */
@Component
public class VodClientImpl implements VodClient {
    @Override
    public R deleteVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("批量删除视频出错了");
    }
}

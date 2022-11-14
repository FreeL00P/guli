package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Api("小节管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video){
        eduVideoService.save(video);
        return R.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable("id") String id){
        //根据小节id获取对应的视频id
        EduVideo video = eduVideoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除小节里的视频
        if (StringUtils.isEmpty(videoSourceId)){
            R r = vodClient.deleteVideo(videoSourceId);
            if (r.getCode()==20001){
                throw new GuliException(20001,"删除失败熔断器");
            }
        }
        //删除小节
        eduVideoService.removeById(id);
        return R.ok();
    }
}


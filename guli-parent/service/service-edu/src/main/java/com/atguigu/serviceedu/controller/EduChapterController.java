package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.chapter.ChapterVo;
import com.atguigu.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Api("课程章节管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;


    @ApiOperation(value = "添加课程章节")
    @CacheEvict(value = "eduChapter",allEntries = true)
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }


    @ApiOperation("根据id查询章节信息")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable("chapterId") String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    @ApiOperation("获取课程章节以及小节信息")
    @Cacheable(key = "'eduChapter'",value = "getChapterVideo")
    @GetMapping("getChapterVideo/{id}")
    public R getChapterVideo(@PathVariable("id") String courseId){
        //根据id获取章节与下面的小节信息
        List<ChapterVo> chapterVideo = eduChapterService.getChapterVideo(courseId);
        return R.ok().data("allChapterVideo",chapterVideo);
    }

    @ApiOperation("根据章节id删除章节")
    @CacheEvict(value = "eduChapter",allEntries = true)
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable("chapterId") String chapterId){
        boolean ret = eduChapterService.removeChapter(chapterId);
        if (ret) return R.ok();
        return R.error();
    }

    @ApiOperation("修改章节信息")
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }
}


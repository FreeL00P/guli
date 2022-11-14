package com.atguigu.serviceedu.service.impl;

import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.entity.chapter.ChapterVo;
import com.atguigu.serviceedu.entity.chapter.VideoVo;
import com.atguigu.serviceedu.mapper.EduChapterMapper;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //查询章节信息
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterWrapper);

        //查询小节信息
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(videoWrapper);
        //最终返回结果list
        List<ChapterVo> chapterVoList = new ArrayList<>();
        //将章节信息写入chapterVoList
        for (EduChapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            //将对应chapter下video信息写入
            ArrayList<VideoVo> videoList = new ArrayList<>();
            for (EduVideo video : eduVideoList) {
                if (chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
            chapterVoList.add(chapterVo);

        }
        return chapterVoList;
    }

    @Override
    public boolean removeChapter(String chapterId) {
        //查询章节下的小节 如果有 则不能删除 抛出自定义异常提示
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0){
            throw new GuliException(20001,"当前章节下包含小节，不能删除");
        }else {
            return this.removeById(chapterId);
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        this.remove(wrapper);
    }
}

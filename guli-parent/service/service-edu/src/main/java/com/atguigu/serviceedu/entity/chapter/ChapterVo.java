package com.atguigu.serviceedu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ChapterVo
 *
 * @author fj
 * @date 2022/11/3 20:30
 */

/**
 * 课程章节类
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    private List<VideoVo> children=new ArrayList<>();
}

package com.atguigu.serviceedu.entity.subject;

/**
 * OneSubject
 *
 * @author fj
 * @date 2022/11/1 15:39
 */

import com.atguigu.serviceedu.entity.EduSubject;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一层分类
 */
@Data
public class OneSubject implements Serializable {
    private String id;
    private String title;
    private List<TwoSubject> children;
}

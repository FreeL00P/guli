package com.atguigu.serviceedu.entity.subject;

/**
 * TwoSubject
 *
 * @author fj
 * @date 2022/11/1 15:39
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 二层分类
 */
@Data
public class TwoSubject implements Serializable {
    private String id;
    private String title;
}

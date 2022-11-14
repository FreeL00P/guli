package com.atguigu.serviceedu.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CourseQuery
 *
 * @author fj
 * @date 2022/11/10 14:04
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private Integer priceSort;

    @ApiModelProperty(value = "销售数量")
    private Integer buyCountSort;

    @ApiModelProperty(value = "创建时间")
    private Integer gmtCreateSort;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

}

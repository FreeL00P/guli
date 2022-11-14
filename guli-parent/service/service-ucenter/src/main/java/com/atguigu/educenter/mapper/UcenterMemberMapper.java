package com.atguigu.educenter.mapper;

import com.atguigu.educenter.entity.ucenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-11-08
 */
public interface UcenterMemberMapper extends BaseMapper<ucenterMember> {

    Integer selectRegisterCount(String day);
}

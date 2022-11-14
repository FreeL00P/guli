package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.ucenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-08
 */
@Service
public interface UcenterMemberService extends IService<ucenterMember> {


    String login(LoginInfoVo member);

    void register(RegisterVo registerVo);

    //根据openid判断

    ucenterMember getOpenIdMember(String openid);

    Integer countRegisterByDay(String day);
}

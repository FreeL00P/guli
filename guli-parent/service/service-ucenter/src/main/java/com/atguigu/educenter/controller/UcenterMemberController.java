package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.ucenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.commonutils.ordervo.UCenterMemberOrder;
import com.atguigu.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-08
 */
@Api("登陆验证")
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
@Slf4j
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation("登陆")
    @PostMapping("login")
    public R login(@RequestBody LoginInfoVo member){
        log.info("进入了登陆方法");
        //调用service
        String token = memberService.login(member);
        if (StringUtils.isEmpty(token)||token.equals("")) return R.error();
        return R.ok().data("token",token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getMemberInfo")
    public R getLoginInfo(HttpServletRequest request){
        //获取memberId
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //调用service获取登陆用户信息
        ucenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);

    }
    //根据token字符串获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UCenterMemberOrder getUserInfo(@PathVariable String id){
        ucenterMember member = memberService.getById(id);
        UCenterMemberOrder uCenterMemberPay = new UCenterMemberOrder();
        BeanUtils.copyProperties(member, uCenterMemberPay);
        return uCenterMemberPay;
    }

    @GetMapping(value = "countregister/{day}")
    public R registerCount(
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister",count);
    }

}


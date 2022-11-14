package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.ucenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.handler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, ucenterMember> implements UcenterMemberService {

    @Override
    public String login(LoginInfoVo member) {
        //获取前端传过来的账号密码
        String mobile = member.getMobile();
        String password=member.getPassword();
        //判断账号是否为空
        if(StringUtils.isEmpty(mobile)){
            throw new GuliException(20001,"请检查你的账号是否输入");
        }
        //判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            throw new GuliException(20001,"请检查你的密码是否输入");
        }
        //到数据库查询
        QueryWrapper<ucenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        ucenterMember member1 = this.getOne(wrapper);
        if (!MD5.encrypt(password).equals(member1.getPassword())){
            throw new GuliException(20001,"账号或密码错误");
        }
        //判断账号是否被禁用
        if (member1.getIsDisabled()){
            throw new GuliException(20001,"你的账号已被禁用，请联系管理员");
        }
        //登陆成功 生成token
        return  JwtUtils.getJwtToken(member1.getId(),member1.getNickname());
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String userCode = registerVo.getCode();
        //判断数据有效性
        if (StringUtils.isEmpty(nickname)){
            throw new GuliException(20001,"用户名不能为空");
        }
        if (StringUtils.isEmpty(mobile)){
            throw new GuliException(20001,"手机号不能为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new GuliException(20001,"密码不能为空");
        }
        if (StringUtils.isEmpty(userCode)){
            throw new GuliException(20001,"验证码不能为空");
        }
        //验证验证码是否正确
        String code = redisTemplate.opsForValue().get(registerVo.getMobile());
        if (!code.equals(userCode)){
            throw new GuliException(20001,"验证码有误，请重新输入");
        }
        Integer count = baseMapper.selectCount(new
                QueryWrapper<ucenterMember>().eq("mobile", registerVo.getMobile()));
        if(count > 0) {
            throw new GuliException(20001,"手机号已经被注册了");
        }

        //完善用户信息
        //添加注册信息到数据库
        ucenterMember member = new ucenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS" +
                "4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }

    //根据openid判断
    @Override
    public ucenterMember getOpenIdMember(String openid) {
        QueryWrapper<ucenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Integer countRegisterByDay(String day) {

        return baseMapper.selectRegisterCount(day);
    }
}

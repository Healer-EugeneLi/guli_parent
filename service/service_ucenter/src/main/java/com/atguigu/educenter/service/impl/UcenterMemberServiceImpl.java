package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.educenter.utils.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-06
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 用户登录
     *
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {

        String email = loginVo.getEmail();
        String password = loginVo.getPassword();
        //1.检查是否为空
        if (StringUtils.isEmpty(email)||StringUtils.isEmpty(password))
            throw new GuliException(20001,"登录失败，信息不完整");

        //2.检查用户是否存在，即邮箱是否存在
        QueryWrapper<UcenterMember> emailWrapper=new QueryWrapper<>();
        emailWrapper.eq("email",email);
        UcenterMember ucenterMember = baseMapper.selectOne(emailWrapper);
        if (ucenterMember==null)
            throw new GuliException(20001,"用户不存在");

        //3.如果用户存在，检查密码是否正确
        //对用户提交的密码进行MD5加密 再与数据库中的密码比较
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new GuliException(20001,"密码不正确");
        }

        //4.检查用户是否被禁用
        if (ucenterMember.getIsDisabled())
            throw new GuliException(20001,"用户已被禁用");

        //5.生成token
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getPassword());

        return token;
    }

    /**
     * 用户注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {

        String email = registerVo.getEmail();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        String code = registerVo.getCode();

        ///1.数据校验
        if (StringUtils.isEmpty(email)||StringUtils.isEmpty(password)
        ||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(code)){
            throw new GuliException(20001,"注册信息不完整，注册失败");
        }

        //2.查看用户是否存在，检查邮箱是否存在
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>0)
            throw new GuliException(20001,"该用户已存在");

        //3.检查验证码是否正确
        String realCode = redisTemplate.opsForValue().get(email);
        if (!code.equals(realCode))
            throw new GuliException(20001,"验证码错误，注册失败");

        //4.符合条件之后，新增用户信息
        UcenterMember member=new UcenterMember();
        member.setEmail(email);//设置邮箱
        member.setPassword(MD5.encrypt(password));//密码
        member.setIsDisabled(false);//设置为用户可用
        member.setNickname(nickname);//设置昵称
        member.setAvatar("https://eugeneli-guli.oss-cn-beijing.aliyuncs.com/2022/03/28/71286670d2a64bf09cbc3cdf2d01ad87girl.jpg");//默认头像
        this.save(member);

    }

    /**
     * 根据用户id获取登录信息
     *
     * @param memberId
     * @return
     */
    @Override
    public LoginInfoVo getLoginVoInfo(String memberId) {

        UcenterMember ucenterMember = this.getById(memberId);

        LoginInfoVo loginInfoVo=new LoginInfoVo();
        BeanUtils.copyProperties(ucenterMember,loginInfoVo);

        if(loginInfoVo==null)
            throw new GuliException(20001,"获取用户登录信息失败");
        return loginInfoVo;
    }

    /**
     * 通过openid获取用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }


}

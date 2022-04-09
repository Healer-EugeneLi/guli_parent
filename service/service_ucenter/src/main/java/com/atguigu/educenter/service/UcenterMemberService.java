package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-06
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

    /**
     * 用户注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据用户id获取登录信息
     * @param memberId
     * @return
     */
    LoginInfoVo getLoginVoInfo(String memberId);

    /**
     * 通过openid获取用户信息
     * @param openid
     * @return
     */
    UcenterMember getByOpenid(String openid);
}

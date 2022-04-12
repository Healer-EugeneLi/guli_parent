package com.atguigu.eduservice.client;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: UcenterClient
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/9
 * @Time: 22:13
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)//远程调用service-ucenter中的服务
public interface UcenterClient {

    //根据用户id获取用户信息
    @GetMapping("/educenter/member/getUserInfo/{id}")
    public UcenterMemberOrder getInfo(@PathVariable("id") String id);
}

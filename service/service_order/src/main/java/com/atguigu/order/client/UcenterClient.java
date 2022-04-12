package com.atguigu.order.client;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName: UcenterClient
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/12
 * @Time: 15:59
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {


    @GetMapping("/educenter/member/getUserInfo/{id}")
    public UcenterMemberOrder getInfo(@PathVariable("id") String id);
}

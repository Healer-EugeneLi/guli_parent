package com.atguigu.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: OrderClient
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/12
 * @Time: 20:29
 */
@Component
@FeignClient(name = "service-order",fallback = OrderClientImpl.class)
public interface OrderClient {

    //查询订单信息
    @GetMapping("isBuyCourse/{memberId}/{id}")
    public boolean isBuyCourse(@PathVariable("memberId") String memberId,
                               @PathVariable("id") String id);
}

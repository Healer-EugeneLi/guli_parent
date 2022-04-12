package com.atguigu.order.service;

import com.atguigu.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-12
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据courseId memberId 创建订单
     * @param courseId
     * @param memberIdByJwtToken
     * @return
     */
    String createOrder(String courseId, String memberIdByJwtToken);
}

package com.atguigu.order.service;

import com.atguigu.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-12
 */
public interface PayLogService extends IService<PayLog> {



    /**
     * 根据订单orderNo生成二维码
     * @param orderNo
     * @return
     */
    Map createNative(String orderNo);

    /**
     * 根据orderNo查询订单状态
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 更新订单状态
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);
}

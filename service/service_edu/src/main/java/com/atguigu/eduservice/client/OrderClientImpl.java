package com.atguigu.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @ClassName: OrderClientImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/12
 * @Time: 20:30
 */
@Component
public class OrderClientImpl implements OrderClient{
    @Override
    public boolean isBuyCourse(String memberId, String id) {
        return false;
    }
}

package com.atguigu.order.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EduClientImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/12
 * @Time: 16:46
 */
@Component
public class EduClientImpl implements EduClient{
    @Override
    public CourseWebVoOrder getCourseWebVoOrder(String courseId) {
        return null;
    }
}

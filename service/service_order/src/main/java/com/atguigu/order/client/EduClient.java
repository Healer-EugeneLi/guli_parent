package com.atguigu.order.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: EduClient
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/12
 * @Time: 15:57
 */
@Component
@FeignClient(name = "service-edu",fallback = EduClientImpl.class)
public interface EduClient {

    @GetMapping("/eduservice/coursefront/getCourseWebVoOrder/{courseId}")
    public CourseWebVoOrder getCourseWebVoOrder(@PathVariable("courseId") String courseId);
}

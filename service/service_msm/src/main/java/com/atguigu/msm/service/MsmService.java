package com.atguigu.msm.service;

import java.util.Map;

/**
 * @ClassName: MsmService
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 16:08
 */

public interface MsmService {

    /**
     * 发送邮箱验证码
     * @param email
     * @param map
     * @return
     */
    boolean send(String email, Map<String, Object> map);
}

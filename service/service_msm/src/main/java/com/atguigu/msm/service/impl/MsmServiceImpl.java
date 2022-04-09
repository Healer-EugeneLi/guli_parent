package com.atguigu.msm.service.impl;

import com.atguigu.msm.service.MsmService;
import com.atguigu.msm.utils.SendMailUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: MsmServiceImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 16:08
 */

@Service
public class MsmServiceImpl implements MsmService {
    /**
     * 发送邮箱验证码
     *
     * @param email
     * @param map
     * @return
     */
    @Override
    public boolean send(String email, Map<String, Object> map) {

        String code=(String) map.get("code");
        //邮件主题
        String emailTitle="邮箱验证";
        //邮件内容
        String emailContext="您正在进行邮箱验证，您的验证码为:"+code+"，请于5分钟内完成验证";

        //发送邮件
        try {
            SendMailUtil.sendEmail(email,emailTitle,emailContext);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

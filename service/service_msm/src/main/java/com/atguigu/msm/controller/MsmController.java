package com.atguigu.msm.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msm.service.MsmService;
import com.atguigu.msm.utils.RandomUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MsmController
 * @Description: 手机短信服务改为邮箱验证码
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 16:04
 */
@RestController
@CrossOrigin
@RequestMapping("edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{email}")
    public R code(@PathVariable String email){

        String code = redisTemplate.opsForValue().get(email);

        if (!StringUtils.isEmpty(code)) return R.ok();

        //随机生成验证码
        code = RandomUtil.getFourBitRandom();

        Map<String,Object> map=new HashMap<>();
        map.put("code",code);
        boolean isSend=msmService.send(email,map);

        if (isSend){
            redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
            System.out.println("发送成功:"+code);
            return R.ok().message("发送邮箱验证码发送成功");
        }else
            return R.error().message("发送邮箱验证码失败");

    }

}

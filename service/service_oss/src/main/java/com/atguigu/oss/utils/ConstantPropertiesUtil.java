package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ConstantPropertiesUtil
 * @Description:常量类，读取配置文件application.properties中的配置
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 15:30
 */
//当项目自己启动之后 spring加载之后执行接口的一个方法
@Component
public class ConstantPropertiesUtil implements InitializingBean {


    //读取配置文件的内容

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //公开的静态常量方便适用
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;


    @Override
    public void afterPropertiesSet() throws Exception {

        END_POINT=endpoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}

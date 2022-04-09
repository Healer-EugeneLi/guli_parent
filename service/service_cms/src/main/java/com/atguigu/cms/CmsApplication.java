package com.atguigu.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: CmsApplication
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/5
 * @Time: 23:25
 */

@SpringBootApplication
@ComponentScan(basePackages ={"com.atguigu"})
@MapperScan("com.atguigu.cms.mapper")
public class CmsApplication {

    public static void main(String[] args) {

        SpringApplication.run(CmsApplication.class,args);
    }
}

package com.atguigu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: UCenterApplication
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 20:23
 */

@SpringBootApplication
@ComponentScan({"com.atguigu"})
@MapperScan("com.atguigu.educenter.mapper")
public class UCenterApplication {

    public static void main(String[] args) {

        SpringApplication.run(UCenterApplication.class,args);

    }
}

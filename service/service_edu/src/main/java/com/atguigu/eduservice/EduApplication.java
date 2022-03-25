package com.atguigu.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: EduApplication
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/17
 * @Time: 11:46
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.atguigu"})
public class EduApplication {

    public static void main(String[] args) {

        SpringApplication.run(EduApplication.class,args);
    }
}



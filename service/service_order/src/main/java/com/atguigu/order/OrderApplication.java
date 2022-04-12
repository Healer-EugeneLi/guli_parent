package com.atguigu.order;

import com.atguigu.order.controller.OrderController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: OrderApplication
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/12
 * @Time: 15:39
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.atguigu"})
@MapperScan("com.atguigu.order.mapper")
@EnableDiscoveryClient//nacos注册
@EnableFeignClients//Feign
public class OrderApplication {
    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class,args);
    }
}

package com.atguigu.order.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.order.entity.Order;
import com.atguigu.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {


    @Autowired
    public OrderService orderService;

    @ApiOperation("创建一个订单")
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){

        String orderNo=orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderNo",orderNo);
    }

    @ApiOperation("根据订单号获取订单信息")
    @GetMapping("getOrder/{orderNo}")
    public R getOrder(@PathVariable String orderNo){

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("order_no",orderNo);

        Order order = orderService.getOne(wrapper);
        if (order==null)
            return R.error();
        return R.ok().data("item",order);
    }

    @ApiOperation("根据用户id和课程id查询订单信息")
    @GetMapping("isBuyCourse/{memberId}/{id}")
    public boolean isBuyCourse(@PathVariable String memberId,
                               @PathVariable String id){
        QueryWrapper<Order> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id).eq("member_id",memberId).eq("status",1);

        int count = orderService.count(wrapper);
        if (count>0)
            return true;
        else
            return false;
    }
}


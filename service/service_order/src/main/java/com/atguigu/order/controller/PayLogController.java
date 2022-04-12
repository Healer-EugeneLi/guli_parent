package com.atguigu.order.controller;


import com.atguigu.commonutils.R;
import com.atguigu.order.service.PayLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @ApiOperation("生成二维码")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){

        Map map=payLogService.createNative(orderNo);
        System.out.println("生成二维码的返回结果:"+map);
        return R.ok().data(map);
    }

    @ApiOperation("查询支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){

        Map<String,String> map= payLogService.queryPayStatus(orderNo);
        if (map==null){
            return R.error().message("支付出错");
        }

        if (map.get("trade_state").equals("SUCCESS")){
            //成功更新订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }


}


package com.atguigu.order.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.order.client.EduClient;
import com.atguigu.order.client.UcenterClient;
import com.atguigu.order.entity.Order;
import com.atguigu.order.mapper.OrderMapper;
import com.atguigu.order.service.OrderService;
import com.atguigu.order.utils.OrderNoUtil;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 根据courseId memberId 创建订单
     *
     * @param courseId
     * @param memberIdByJwtToken
     * @return
     */
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {

        //1.根据courseId获取详细的课程详情
        CourseWebVoOrder courseWebVoOrder = eduClient.getCourseWebVoOrder(courseId);

        //2.根据memberId获取用户信息
        UcenterMemberOrder memberOrder = ucenterClient.getInfo(memberIdByJwtToken);

        if (courseWebVoOrder==null)
            throw new GuliException(20001,"获取课程详情失败");
        if (memberOrder==null)
            throw new GuliException(20001,"获取用户信息失败");

        Order order=new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//设置随机生成的订单号
        //设置课程相关的信息
        order.setCourseId(courseId);//课程id
        order.setCourseTitle(courseWebVoOrder.getTitle());//课程标题
        order.setCourseCover(courseWebVoOrder.getCover());//课程封面
        order.setTeacherName(courseWebVoOrder.getTeacherName());//课程老师名字
        order.setTotalFee(courseWebVoOrder.getPrice());//课程价格

        //设置用户信息相关
        order.setMemberId(memberIdByJwtToken);//用户id
        order.setNickname(memberOrder.getNickname());//昵称
        order.setEmail(memberOrder.getEmail());//用户邮箱

        //订单相关
        order.setStatus(0);//0 未支付 1支付
        order.setPayType(1);//支付类型：  1 微信  2 支付宝


        int insert = baseMapper.insert(order);
        if (insert==0)
            throw new GuliException(20001,"生成订单失败");
        return order.getOrderNo();
    }
}

package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.api.scripting.ScriptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {

    @Autowired
    public EduCommentService commentService;

    @Autowired
    public UcenterClient ucenterClient;

    @ApiOperation("评论分页列表")
    @GetMapping("pageList/{page}/{limit}")
    public R index(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                   @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                   @ApiParam(name = "courseQuery", value = "查询对象", required = false) String courseId){

        //构建一个分页的对象 参数是page limit
        Page<EduComment> commentPage=new Page<>(page,limit);
        //构建一个条件查询
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("course_id",courseId);
        //使用service进行调用
        commentService.page(commentPage,wrapper);

        //此时page对象已经被更新 直接返回相应的数据
        List<EduComment> records = commentPage.getRecords();

        Map<String,Object> map=new HashMap<>();
        map.put("items",records);
        map.put("current",commentPage.getCurrent());
        map.put("pages",commentPage.getPages());
        map.put("size",commentPage.getSize());
        map.put("total",commentPage.getTotal());
        map.put("hasNext",commentPage.hasNext());
        map.put("hasPrevious",commentPage.hasPrevious());
        return R.ok().data("map",map);
    }

    @ApiOperation("添加评论")
    @PostMapping("auth/save")
    public R saveCommit(@RequestBody EduComment comment, HttpServletRequest request){



        //先判断是否已经登录过 使用jwt
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberIdByJwtToken)){
            return R.error().code(28004).message("请登录");
        }

        //课程评论不能为空
        if (comment.getContent().isEmpty()){
            throw new GuliException(20001,"评论不能为空");
        }


        //如果登录过 则通过远程服务调用 请求service-ucenter中的 通过id获取信息
        UcenterMemberOrder memberOrder = ucenterClient.getInfo(memberIdByJwtToken);
        System.out.println(memberOrder);
        comment.setMemberId(memberIdByJwtToken);//用户id
        comment.setAvatar(memberOrder.getAvatar());//用户头像
        comment.setNickname(memberOrder.getNickname());//用户昵称
        commentService.save(comment);
        return R.ok().message("评论成功");
    }


}


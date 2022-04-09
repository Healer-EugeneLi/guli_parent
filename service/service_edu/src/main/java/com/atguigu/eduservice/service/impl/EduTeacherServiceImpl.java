package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-17
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery) {


        QueryWrapper<EduTeacher> wrapper=new QueryWrapper();
        wrapper.orderByDesc("gmt_create");

        //当查询为空的时候 等同于全部查询
        if (teacherQuery==null){
            baseMapper.selectPage(pageTeacher,wrapper);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }

        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }

        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        baseMapper.selectPage(pageTeacher,wrapper);
    }

    //----------------------------------------------------------前端界面部分---------------------------------------

    /**
     * 前端获取名师列表
     *
     * @param pageTeacher
     * @return
     */
    @Override
    public Map<String, Object> getTeacherPageList(Page<EduTeacher> pageTeacher) {


        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");

        baseMapper.selectPage(pageTeacher,wrapper);

        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        long size = pageTeacher.getSize();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        boolean hasNext = pageTeacher.hasNext();
        boolean hasPrevious = pageTeacher.hasPrevious();

        Map<String,Object> map=new HashMap<>();
        map.put("records",records);
        map.put("total",total);
        map.put("size",size);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }
}

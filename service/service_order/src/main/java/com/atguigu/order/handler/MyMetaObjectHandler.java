package com.atguigu.order.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: MyMetaObjectHandle
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/18
 * @Time: 14:56
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}

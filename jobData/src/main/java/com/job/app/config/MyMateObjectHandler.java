package com.job.app.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMateObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",
                new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(new Date()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyTime",
                new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(new Date()), metaObject);
    }
}

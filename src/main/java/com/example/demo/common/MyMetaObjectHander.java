package com.example.demo.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//数据库填充处理器
//一定不要忘记把处理器加到IOC容器中

//导入日志
@Slf4j
@Component
public class MyMetaObjectHander implements MetaObjectHandler {
//    插入时候的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
//        日志
        log.info("start insert fill.....");
//        setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("createtime",new Date(),metaObject);
        this.setFieldValByName("updatetime",new Date(),metaObject);
    }
//    更新后的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        //        日志
        log.info("start update fill.....");
//        setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("updatetime",new Date(),metaObject);
    }
}

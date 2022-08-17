package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Date;


//使用插件，让其与数据库的表名一一对应
@TableName("user")
@Data
public class Usertest {
    //    id定义为自增,数据库主键名字如果不是id，必须加上@TableId(value="")
//    @TableId(type = IdType.AUTO)
    public Long id;
    public String name;
    public Integer age;
    public String email;

//    字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    public Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Date updatetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

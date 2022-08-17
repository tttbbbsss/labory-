package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Usertest;
import com.example.demo.mapper.UserTestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {
    @Autowired
    public UserTestMapper userTestMapper;
    //		测试查询全部参数
//	@Test
    void contextLoads() {
//		查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<Usertest> wapper = new QueryWrapper<>();
        wapper
                .isNotNull("name")
                    .isNotNull("email")
                        .ge("age",12);
        userTestMapper.selectList(wapper).forEach(System.out::println);
    }

    //		测试查询一个参数
//    @Test
    void test2() {
        QueryWrapper<Usertest> wapper = new QueryWrapper<>();
        wapper
                .eq("name","tbs");
//        查询一个数据，出现多个结果使用List或者map
        Usertest users = userTestMapper.selectOne(wapper);
        System.out.println(users);
    }

    //		测试查询年龄在20-30之间
//    @Test
    void test3() {
        QueryWrapper<Usertest> wapper = new QueryWrapper<>();
        wapper
                .between("age",20,30);
//        selectCount查询结果数
        Integer couts = userTestMapper.selectCount(wapper);
        System.out.println(couts);
    }

}

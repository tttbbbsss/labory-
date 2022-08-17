package com.example.demo.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;

import com.example.demo.entity.Datamation;
import com.example.demo.mapper.DatamationMapper;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//1.定义这是一个返回json的Controller
@RestController
//2.定义一个路由
@RequestMapping("/datamation")
@Api(value = "基础数据接口", tags = "数据接口")
public class DatamationController {
    @Resource
    DatamationMapper datamationMapper;

    //    3.定义一个post接口
//    <?>表示任何一种类型
//    PostMapping指新增
    @PostMapping
    public Result<?> save(@RequestBody Datamation datamation){
        datamationMapper.insert(datamation);
        return Result.success();
    }


    //    PutMapping指更新
    @PutMapping
    public Result<?> update(@RequestBody Datamation datamation){
        datamationMapper.updateById(datamation);
        return Result.success();
    }

    //    @DeleteMapping指删除
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        datamationMapper.deleteById(id);
        return Result.success();
    }

    //GetMapping指查询
    //    定义一个get请求
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize){
        LambdaQueryWrapper<Datamation> wrapper = Wrappers.<Datamation>lambdaQuery();
//        创建分页对象
        Page<Datamation> userPage= datamationMapper.selectPage(new Page<>(pageNum, pageSize),wrapper );
        return Result.success(userPage);
    }
}

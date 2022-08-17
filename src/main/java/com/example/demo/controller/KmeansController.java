package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.service.Kmeansservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/julei")
//说明接口文件
@Api(value = "聚类接口", tags = "聚类接口", description = "聚类测试接口")
public class KmeansController {
    @Autowired
    private Kmeansservice kmeansservice;

    @GetMapping
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "meansList", notes = "输入原始list坐标点位信息（JOSN格式）")
    public Result<?> Tsp(@RequestBody ArrayList<float[]> dataSet,@RequestParam(defaultValue = "1") Integer numberclu) throws Exception {
//        ArrayList<ArrayList<float[]>> cluster;
        ArrayList<float[]> center;
        center=kmeansservice.Run(dataSet,numberclu);
        return Result.success(center);
    }
}

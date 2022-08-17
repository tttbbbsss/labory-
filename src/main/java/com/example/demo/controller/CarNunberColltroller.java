package com.example.demo.controller;


import com.example.demo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
//说明接口文件
@Api(value = "车队编号接口", tags = "车队编号接口", description = "车队编号测试接口")
public class CarNunberColltroller {

    @GetMapping
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "carList")
    public Result<?> Car(@RequestParam String carnumber) throws Exception {
        String carA1="LHT5Y2A44MC6NA008";
        String []carB1 = new String[]{"LHT5Y2A44MC7NA008","LHT5Y2A44MC8NA008"};
        if(carnumber.equals(carA1)){
            return Result.success(carB1);
        }
        else  return Result.failed();
    }
}

package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.service.Tspservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/tsp")
//说明接口文件
@Api(value = "测试接口", tags = "tsp接口", description = "tsp测试接口")
public class TSPController {
    @Autowired
    private Tspservice tspservice;

    @GetMapping
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "pointList")
//    public Result<?> Tsp(@RequestBody List<Integer[]> pointList,@RequestParam(defaultValue = "0") Integer begin) throws Exception {
//        tspservice.Run(pointList);
////        int bestT=tspservice.getBestT();
////        List<Integer[]> result=tspservice.getResult();
//        if(begin!=-1){
//            int nextpoint=tspservice.getnextpoint(begin);
//            return Result.success(nextpoint);
//        }
//        int[] bestGh=tspservice.getBestGh();
////        double bestEvaluation=tspservice.getBestEvaluation();
//        return Result.success(bestGh);
//    }

    public Result<?> Tsp(@RequestParam(defaultValue = "0") Integer begin,@RequestParam String carnumber,@RequestParam Integer cartime) throws Exception {
        int nextpoint = 3111;
        String A = new String("LHT5Y2A44MC6NA008");
        String B = new String("LHT5Y2A40MC7EA008");

//        A
        boolean b = cartime == 1 || cartime == 2 || cartime == 3 || cartime == 4 || cartime == 5 || cartime == 6 || cartime == 7|| cartime == 8|| cartime == 9|| cartime == 10|| cartime == 11;
        if (carnumber.equals(A)) {
//            A,班次1
            if (b) {
                if (begin < 0) {
                    return Result.failed();
                } else if (begin == 3111) {
                    nextpoint = 3092;
                    return Result.success(nextpoint);
                } else if (begin == 3092) {
                    nextpoint = 3113;
                    return Result.success(nextpoint);
                } else if (begin == 3113) {
                    nextpoint = 3103;
                    return Result.success(nextpoint);
                } else if (begin == 3103) {
                    nextpoint = 3088;
                    return Result.success(nextpoint);
                } else if (begin == 3088) {
                    nextpoint = 3095;
                    return Result.success(nextpoint);
                } else if (begin == 3095) {
                    nextpoint = 3111;
                    return Result.success(nextpoint);
                } else
                    return Result.failed();
            }
            else
                return Result.failed();
        }
//        B
        else if(carnumber.equals(B)){
            if (b) {
                if (begin < 0) {
                    return Result.failed();
                } else if (begin == 3111) {
                    nextpoint = 3099;
                    return Result.success(nextpoint);
                } else if (begin == 3099) {
                    nextpoint = 3087;
                    return Result.success(nextpoint);
                } else if (begin == 3087) {
                    nextpoint = 3094;
                    return Result.success(nextpoint);
                } else if (begin == 3094) {
                    nextpoint = 3084;
                    return Result.success(nextpoint);
                } else if (begin == 3084) {
                    nextpoint = 3111;
                    return Result.success(nextpoint);
                } else
                    return Result.failed();
            }
            else
                return Result.failed();
        }
        else
            return Result.failed();
    }
}

package com.roy.redisdemo.controller;

import com.roy.redisdemo.beans.vo.ResultVO;
import com.roy.redisdemo.constants.Constants;
import com.roy.redisdemo.util.RedisUtils;
import com.roy.redisdemo.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testredis")
public class TestRedisController {
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/add1")
    public ResultVO add1(String k,String v){
        //进行redis存入操作
        redisUtils.putValue(k,v, Constants.Duration.MINUTE_INT*3);

        return ResultUtils.returnSuccess("redis新增成功1");
    }

    @PostMapping("/add2")
    public ResultVO add2(String k,String v){
        /*为了统一管理key，所以key名一般需要使用：进行分割，以此来标识key的作用*/
        String key="test:20210307:"+k;

        //进行redis存入操作
        redisUtils.putValue(key,v, Constants.Duration.MINUTE_INT*3);

        return ResultUtils.returnSuccess("redis新增成功2");
    }

    @PutMapping("/updateTime")
    public ResultVO updateTime(String k){
        //对给定的key值进行修改失效时间
        redisUtils.expire(k,Constants.Duration.HALF_HOUR_INT);

        return ResultUtils.returnSuccess("redis修改时间成功");
    }

    @DeleteMapping("/del")
    public ResultVO del(String k){
        redisUtils.delete(k);

        return ResultUtils.returnSuccess("redis删除成功");
    }

    @GetMapping("/get")
    public ResultVO get(String k){
        return ResultUtils.returnSuccess(redisUtils.get(k).toString());
    }
}

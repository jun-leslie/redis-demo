package com.roy.redisdemo.controller;

import com.roy.redisdemo.constants.Constants;
import com.roy.redisdemo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/sms",produces = "text/html;charset=utf-8")
public class SMSCtroller {
    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("sendSMS")
    public String sendSMS(String phone){
        if(phone ==null){
            return JsonUtils.toJsonString(ResultUtils.returnFail("手机号不能为空"));
        }

        //产生redis的key，项目编码:业务编码:手机号码:0(登录注册专用)
        String key= StringUtils.formatKeyWithPrefix(
                Constants.RedisKey.PROJECT_PRIFIX,
                Constants.RedisKey.SMS_PRIFIX,
                phone,
                Constants.Sms.CodeType.LOGIN_OR_REGISTER+"");

        String value=redisUtils.getValue(key);
        if(value !=null){
            return JsonUtils.toJsonString(ResultUtils.returnFail("上一个验证码未到期"));
        }

        //产生随机码，4位
        String randomValue= MathUtils.random();

        //将key和随机码存入redis，有效时间120秒
        redisUtils.putValue(key,randomValue,Constants.Duration.MINUTE_INT*2);

        //发送腾讯短信
        boolean flag=smsUtils.sendTencentSMS(phone,randomValue,"2");

        if(flag){
            return JsonUtils.toJsonString(ResultUtils.returnSuccess("验证码发送成功"));
        }
        return JsonUtils.toJsonString(ResultUtils.returnFail());
    }
}

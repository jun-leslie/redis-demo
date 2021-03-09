package com.roy.redisdemo.controller;

import com.roy.redisdemo.constants.Constants;
import com.roy.redisdemo.util.JsonUtils;
import com.roy.redisdemo.util.RedisUtils;
import com.roy.redisdemo.util.ResultUtils;
import com.roy.redisdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/user",produces = "text/html;charset=utf-8")
public class UserController {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("login/sms")
    public String loginBySms(String phone,String code){
        //产生redis的key，项目编码:业务编码:手机号码:0(登录注册专用)
        String key= StringUtils.formatKeyWithPrefix(
                Constants.RedisKey.PROJECT_PRIFIX,
                Constants.RedisKey.SMS_PRIFIX,
                phone,
                Constants.Sms.CodeType.LOGIN_OR_REGISTER+"");

        String value=redisUtils.getValue(key);

        if(value ==null){
            return JsonUtils.toJsonString(ResultUtils.returnFail("验证码失效"));
        }else if(!value.equals(code)){
            return JsonUtils.toJsonString(ResultUtils.returnFail("验证码错误"));
        }

        //删除redis中的验证码
        redisUtils.delete(key);

        String token=createToken(phone);

        if(token !=null){
            return JsonUtils.toJsonString(ResultUtils.returnDataSuccess(StringUtils.createSimpleMap("token",token)));
        }

        return JsonUtils.toJsonString(ResultUtils.returnFail());
    }

    public String createToken(String phone){
        String token= StringUtils.createToken();

        //保存token，有效期2小时
        String tokenKey= StringUtils.formatKeyWithPrefix(Constants.RedisKey.PROJECT_PRIFIX,Constants.RedisKey.TOKEN_PRIFIX,token);
        redisUtils.set(tokenKey, phone,Constants.Duration.HOUR_INT*2);

        return token;
    }
}

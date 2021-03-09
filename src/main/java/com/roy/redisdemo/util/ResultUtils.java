package com.roy.redisdemo.util;

import com.roy.redisdemo.beans.vo.ResultVO;

public class ResultUtils {
    public static ResultVO returnFail(){
        return new ResultVO("101","错误");
    }

    public static ResultVO returnFail(String msg){
        return new ResultVO("101",msg);
    }

    public static ResultVO<String> returnSuccess(){
        return new ResultVO("201","成功");
    }

    public static ResultVO returnSuccess(String msg){
        return new ResultVO("201",msg);
    }

    public static ResultVO returnDataSuccess(Object data){
        return new ResultVO("201",data);
    }
}

package com.roy.redisdemo.beans.vo;

public class ResultVO<T> {
    private String code;
    private String msg;
    private T data;

    public ResultVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultVO(T data) {
        this.data = data;
    }

    public ResultVO() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

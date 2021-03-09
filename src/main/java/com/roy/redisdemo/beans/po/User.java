package com.roy.redisdemo.beans.po;

public class User {
    private int id;
    private String loginName;
    private String phone;
    private String loginPass;
    private String fullName;

    public User(int id, String loginName, String phone, String loginPass, String fullName) {
        this.id = id;
        this.loginName = loginName;
        this.phone = phone;
        this.loginPass = loginPass;
        this.fullName = fullName;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

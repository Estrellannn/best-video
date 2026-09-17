package com.aoba.aiqiyi.Entiy;

// 通用返回类
public class R {
    private Integer code;
    private String msg;

    public static R ok(String msg){
        R r = new R();
        r.code=200;
        r.msg=msg;
        return r;
    }

    public static R fail(String msg){
        R r = new R();
        r.code=500;
        r.msg=msg;
        return r;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

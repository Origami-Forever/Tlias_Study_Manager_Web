package com.example.pojo;

import lombok.Data;

@Data
public class Result<T> {
    // 响应码 1成功 0失败
    private Integer code;
    // 提示信息
    private String msg;
    // 响应数据
    private T data;

    // 成功无数据
    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.setCode(1);
        r.setMsg("操作成功");
        return r;
    }

    // 成功带数据
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode(1);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    // 失败
    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.setCode(0);
        r.setMsg(msg);
        return r;
    }
}

package com.example.demo.common;

public class Result<T> {
//    code状态码
    private String code;
//    msg信息
    private String msg;
//        泛型T，data可以是任何
    private T data;



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

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> failed(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("100");
        result.setMsg("失败");
        return result;
    }

    public static Result failed() {
        Result result = new Result<>();
        result.setCode("100");
        result.setMsg("失败");
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

package com.codeyuaiiao.config;

import java.util.Map;

/**
 *返回结果类统一封装
 */
public class RestResult {
    //状态码
    private int code;
    //消息
    private String message;
    //额外的内容
    private Object data;
    //token
    private String token;

    public RestResult(){

    }

    public RestResult setCode(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public RestResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }

    public String getToken(){return token;}

    public RestResult setToken(String token){
        this.token=token;
        return this;
    }

}
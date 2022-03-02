package com.codeyuaiiao.config;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResultGenerator {

    private static final String SUCCESS = "success";
    //成功
    public RestResult getSuccessResult() {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS);
    }
    //成功，附带额外数据
    public  RestResult getSuccessResult(Object data) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data);
    }

    public  RestResult getSuccessResult(String message) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message);
    }

    public  RestResult getSuccessResult(Object data, String token) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data)
                .setToken(token);
    }
    public RestResult getTokenResult(String token){
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setToken(token);
    }
    //成功，自定义消息及数据
    public  RestResult getSuccessResult(String message,Object data) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }
    //失败，附带消息
    public  RestResult getFailResult(String message) {
        return new RestResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
    //失败，自定义消息及数据
    public RestResult getFailResult(String message, Object data) {
        return new RestResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }
    //自定义创建
    public RestResult getFreeResult(ResultCode code, String message, Object data) {
        return new RestResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
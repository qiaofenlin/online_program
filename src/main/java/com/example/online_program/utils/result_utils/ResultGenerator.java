package com.example.online_program.utils.result_utils;

/**
 * @Author: qfl
 * @Date: 19-1-12 下午4:58
 * @Description: 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message,ResultCode resultCode) {
        System.out.println("message " + message + "  resultCode  " + resultCode);
        Result r = new Result()
                .setCode(resultCode)
                .setMessage(message);
        System.out.println("*********************   "+r.toString());
        return r;
    }

}

package com.example.online_program.advice;

import com.example.online_program.utils.result_utils.Result;

/**
 * @program: online_program
 * @description: 创建一个自定义异常，用来实验捕获该异常，并返回json
 * @author: qfl
 * @create: 2019-03-15 13:49
 */
public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }
}

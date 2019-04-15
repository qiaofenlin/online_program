package com.example.online_program.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: online_program
 * @description: 异常统一处理
 * @author: qfl
 * @create: 2019-03-15 13:42
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        Result r = new Result();
        r.setMessage(e.getMessage());
        r.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        r.setData(DEFAULT_ERROR_VIEW);
        return r;
    }

}

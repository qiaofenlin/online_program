package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Created by  qfl
 * @Date 19-1-31
 * @Class: TerminalController.java
 * @package_name: online_program
 * @Description: 对用户所属的终端操作
 */
public class TerminalController {

    /**
     * TODO 用户对自己所属项目进行操作
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/terminal/create/")
    public Result TerminalCreate(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/terminal/edit/")
    public Result TerminalEdit(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/terminal/get/")
    public Result TerminalGetInfo(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/terminal/delete/")
    public Result TerminalGetDelete(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }
}

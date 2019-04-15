package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by  qfl
 * @Date 19-1-31
 * @Class: ProjectRuningController.java
 * @package_name: online_program
 * @Description:
 *      1. 用户项目的启动和暂停
 */

@RestController
public class ProjectRuningController {

    /**
     * TODO 用户项目的启动和暂停
     * @param jsonParam
     * @return
     * java 调用shell命令 https://www.cnblogs.com/shihaiming/p/5884859.html
     */
    @PostMapping("/api/projectRunning/start/")
    public Result ProjectStart(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }
}

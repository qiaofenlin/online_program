package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import com.example.online_program.utils.run_python_utils.RunPython;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Created by  qfl
 * @Date 19-1-31
 * @Class: ProjectRuningController.java
 * @package_name: online_program
 * @Description:
 *      1. 用户项目的启动和暂停
 */

@RestController
public class ProjectRuningController extends BaseController{
    public Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * TODO 用户项目的启动和暂停
     * @param jsonParam
     * @return
     * java 调用shell命令 https://www.cnblogs.com/shihaiming/p/5884859.html
     */
    @PostMapping("/api/projectRunning/start/")
    public Result ProjectStart(@RequestBody JSONObject jsonParam) throws Exception {
        RunPython runPython = new RunPython();
        List<String> list = runPython.run("/home/qiao/online_program/src/test/java/testtt.py");
//        System.out.println(StringUtils.join(list.toArray()));
        logger.info("result" + list);
        Result result = ResultGenerator.genSuccessResult(list);
        System.out.println(result.toString());
        return result;
    }
}

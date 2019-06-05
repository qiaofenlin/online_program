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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        String filename = (String) jsonParam.getJSONObject("data").get("filename");
        String file_id = (String) jsonParam.getJSONObject("data").get("file_id");
        String code = (String) jsonParam.getJSONObject("data").get("code");
//        File file = new File("/home/qiao/online_program/src/main/java/com/example/online_program/controller/files"+filename);
        File file = new File(filename);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(code);
        bw.close();

        System.out.println("Done");

        List<String> list = runPython.run(filename);
//        System.out.println(StringUtils.join(list.toArray()));
        logger.info("result" + list);
        Result result = ResultGenerator.genSuccessResult(list);
        System.out.println(result.toString());
        return result;
    }
}

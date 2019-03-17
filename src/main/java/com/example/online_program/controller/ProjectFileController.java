package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


/**
 * @Created by  qfl
 * @Date 19-1-31
 * @Class: ProjectFileController.java
 * @package_name: online_program
 * @Description: 用户项目文件的操作。
 */
@RestController
public class ProjectFileController {

    Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * TODO 用户创建文件 修改文件树
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/projectFile/create/")
    public Result ProjectFileCreate(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    /**
     * TODO 用户修改一个或多个文件 修改文件树
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/projectFile/update/")
    public Result ProjectFileUpdate(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @GetMapping("/api/projectFile/list/")
    public Result ProjectFileList(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    /**
     * TODO 删除文件 修改语法树
     *
     * @param jsonParam
     * @return
     */
    @GetMapping("/api/projectFile/delete/")
    public Result ProjectFileDelete(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    /**
     * TODO 上传文件
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/projectFile/files/upload/")
    public Result ProjectFileUpload(@RequestBody JSONObject jsonParam) throws IOException {
        logger.debug("[ request args ]: " + jsonParam.toJSONString());
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
//        String File_path = "/home/qiao/online_program/notes/test.py";
        String File_path = "/home/qiao/java_pojo/online_program/notes/test.py";
        // TODO 获取文件路径
        outSTr = new FileOutputStream(new File(File_path));
        Buff = new BufferedOutputStream(outSTr);
        long begin0 = System.currentTimeMillis();
        Buff.write(jsonParam.get("data").toString().getBytes());
        Buff.flush();
        Buff.close();
        long end0 = System.currentTimeMillis();
        System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
        Result result = ResultGenerator.genSuccessResult(jsonParam);
        return result;
    }
}

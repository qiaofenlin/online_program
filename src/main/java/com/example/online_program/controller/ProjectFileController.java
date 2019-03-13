package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by  qfl
 * @Date 19-1-31
 * @Class: ProjectFileController.java
 * @package_name: online_program
 * @Description: 用户项目文件的操作。
 */
@RestController
public class ProjectFileController {

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
    public Result ProjectFileDeletc(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }


    /**
     * @Author qfl
     * @Description //TODO 上传json字段写入文件中
     * @Date 上午11:41 19-3-8
     * @Param [jsonParam]
     * @return com.example.online_program.utils.result_utils.Result
     **/
    @GetMapping("/api/projectFile/upload/")
    public Result ProjectFileUpload(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

}

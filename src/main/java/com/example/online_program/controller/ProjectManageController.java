package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectManageController.java
 * @package_name: online_program
 * @Description: 对用户所拥有项目的操作
 */
@RestController
public class ProjectManageController {

    @PostMapping("/api/projectManage/create/")
    public Result ProjectCreate(@RequestBody JSONObject jsonParam) {
//        System.out.println("**********************************************" + jsonParam.toJSONString());
//
//        // TODO todo pojo
//        Userinfo userinfo = JSON.parseObject(String.valueOf(jsonParam), Userinfo.class);
//        System.out.println("***********************************************toString()"+userinfo.toString());
//        Userinfo user = userRepository.save(userinfo); user.toString()
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @GetMapping("/api/projectManage/list/")
    public Result ProjectQueryList(@RequestParam("pageSize") String pageSize,
                                   @RequestParam("page") String page) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/projectManage/edit/")
    public Result ProjectEdit(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @GetMapping("/api/projectManage/delete/")
    public Result ProjectDelete(@RequestParam("id") String id) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }


}

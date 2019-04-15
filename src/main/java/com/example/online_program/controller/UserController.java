package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.service.UserService;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by  qiao
 * @date 18-12-9 下午2:25
 * @Description: 用户管理
 */

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * TODO 用户创建 基本数据创建。
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/user/create/")
    public Result UserCreate(@RequestBody JSONObject jsonParam) {
        Userinfo userinfo = JSON.parseObject(String.valueOf(jsonParam), Userinfo.class);
        Userinfo user = userService.insertUserInfo(userinfo);
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/user/edit/")
    public Result UserEdit(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/user/list/")
    public Result UserQueryList(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/user/id/")
    public Result UserQueryOne(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/user/delete/")
    public Result UserDelete(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }


    @PostMapping("/api/user/login/")
    public Result UserCLogin(@RequestBody JSONObject args) {
        Userinfo userinfo = JSON.parseObject(String.valueOf(args.getJSONObject("data")), Userinfo.class);
        System.out.println("****************************  "+ args.getJSONObject("data"));
        System.out.println("****************************  "+ userinfo.getUserName());
        System.out.println("****************************  "+ userinfo.getPwd());
        Boolean result = userService.checkNameAndPwd(userinfo.getUserName(),userinfo.getPwd());
        if (result){
            return ResultGenerator.genSuccessResult();
        }else{
            String result_data = "用户名或密码错误";
            return ResultGenerator.genSuccessResult(result_data);
        }

    }

    /**
     * TODO 给用户在系统中创建相应的用户 并设置该用户对文件操作的权限
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/user/practice_env/create/")
    public Result UserPracticeEnvCreate(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }
}

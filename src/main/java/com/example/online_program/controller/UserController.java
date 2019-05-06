package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.service.UserService;
import com.example.online_program.utils.Utils;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by  qiao
 * @date 18-12-9 下午2:25
 * @Description: 用户管理
 */

@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登录
     *
     * @param args
     * @return
     */
    @PostMapping("/api/user/login/")
    public Result UserCLogin(@RequestBody JSONObject args) {

        Userinfo userinfo = JSON.parseObject(String.valueOf(args.getJSONObject("data")), Userinfo.class);
        String result = userService.checkNameAndPwd(userinfo.getTel(), userinfo.getPwd());
        if (result.equals("error")) {
            String result_data = "用户名或密码错误";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genSuccessResult(result_data);
        } else {

            Map<String, Object> return_data = new HashMap<String, Object>();
            return_data.put("token",result);
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +return_data);
            return ResultGenerator.genSuccessResult(return_data);
        }
    }

    /**
     * 用户创建 基本数据创建。
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/user/create/")
    public Result UserCreate(@RequestBody JSONObject jsonParam) {
        Userinfo userinfo = JSON.parseObject(String.valueOf(jsonParam), Userinfo.class);
        userinfo.setToken(Utils.getUUIDString());
        Userinfo user = userService.insertUserInfo(userinfo);
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/user/edit/")
    public Result UserEdit(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

//    @PostMapping("/api/user/list/")
//    public ResultPage<Userinfo> queryByPageUserList(@RequestBody ResultPage<Userinfo> result, Userinfo example) {
//        result.setExample(example);//设置前台条件，没有则为null
//        userService.queryByPage(result);//调用service中的查询方法
//        result.setCode("0");//设置状态码
//        return result;
//        Userinfo userinfo = JSON.parseObject(String.valueOf(jsonParam), Userinfo.class);
//        userService.getUserList(1,10);
//        Result result = ResultGenerator.genSuccessResult();
//        return result;
//    }

    @PostMapping("/api/user/list/simple/")
    public Result queryByPageUserListSimple(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.getJSONObject("data"));
//      UserController page = JSON.parseObject(String.valueOf(jsonParam.getJSONObject("data")), UserController.class);
        Result result = userService.getUserList(1,10);
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


    /**
     * TODO 给用户在系统中创建相应的用户 并设置该用户对文件操作的权限
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/user/practice_env/create/")
    public Result UserPracticeEnvCreate(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }
}

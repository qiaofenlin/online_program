package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.service.UserService;
import com.example.online_program.service.UsersStarService;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersStarController extends BaseController {
    @Autowired
    UsersStarService usersStarService;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(UsersStarController.class);

    @PostMapping("/api/stars/list/")
    public Result UsersStarsList(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            logger.info("user_id "+user_id);
            List<Integer> userStarsList= usersStarService.getUserStarsList(user_id,1,10);
            System.out.println("-----------------"+userStarsList.toString());
            Result result = userService.getUserListByUserId(userStarsList, 1, 10);

            return result;
        } else {
            String result_data = "用户名或密码错误";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genSuccessResult();
        }
    }


}

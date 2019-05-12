package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.SimpleCodeInfo;
import com.example.online_program.entity.UsersStarInfo;
import com.example.online_program.service.SimpleCodeService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SimpleCodeController extends BaseController {
    @Autowired
    SimpleCodeService simpleCodeService;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(SimpleCodeController.class);

    /**
     * 获取测试代码列表
     * @param args
     * @return
     */
    @PostMapping("/api/simple/code/list/")
    public Result getSimpleCodeList(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            Result result= simpleCodeService.getSimpleCodeList(user_id);
            return result;
        } else {
            String result_data = "用户名或密码错误";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genSuccessResult();
        }
    }
    /**
     * 添加SimpleCode
     * @param args
     * @return
     */
    @PostMapping("/api/simple/code/add/")
    public Result addSimpleCodeFollowes(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            if (user_id == 0) {
                return ResultGenerator.genFailResult("用户不存在。");
            }else {
                SimpleCodeInfo simpleCodeInfo = new SimpleCodeInfo();
                simpleCodeInfo.setContent(String.valueOf(args.getJSONObject("data").get("content")));
                simpleCodeInfo.setUser_id(user_id);
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                System.out.println(dateFormat.format(date));
                simpleCodeInfo.setCode_time(dateFormat.format(date));
                logger.info("simpleCodeInfo "+simpleCodeInfo.toString());
                simpleCodeService.addSimpleCode(simpleCodeInfo);
                return ResultGenerator.genSuccessResult();
            }
        } else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }
    }


    /**
     * del SimpleCode
     * @param args
     * @return
     */
    @PostMapping("/api/simple/code/del/")
    public Result delSimpleCode(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            int code_id = (Integer) args.getJSONObject("data").get("code_id");
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            if (user_id == 0) {
                return ResultGenerator.genFailResult("用户不存在。");
            }else {
                simpleCodeService.delUserStar(code_id,user_id);
                return ResultGenerator.genSuccessResult();
            }
        } else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }
    }

}

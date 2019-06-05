package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.UsersStarInfo;
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
public class UsersStarController extends BaseController {
    @Autowired
    UsersStarService usersStarService;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(UsersStarController.class);

    /**
     * 获取followering列表
     * @param args
     * @return
     */
    @PostMapping("/api/followering/list/")
    public Result UsersStarsList(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            List<Integer> userStarsList= usersStarService.getUserStarsList(user_id,1,10);
            logger.info("[followering userid "+user_id +" followering len "+userStarsList.size()+"] ");
            Result result = userService.getUserListByUserId(userStarsList, 1, 10);
            return result;
        } else {
            String result_data = "验证信息错误";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genSuccessResult();
        }
    }

    /**
     * 获取followers列表
     * @param args
     * @return
     */
    @PostMapping("/api/followers/list/")
    public Result UsersFollowesList(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            logger.info("user_id "+user_id);
            List<Integer> userStarsList= usersStarService.getUserFollowersList(user_id,1,10);
            logger.info("[userStarsList len "+userStarsList.size() + " ]");
            Result result = userService.getUserListByUserId(userStarsList, 1, 10);

            return result;
        } else {
            String result_data = "用户名或密码错误";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genSuccessResult();
        }
    }

    /**
     * 添加followers
     * @param args
     * @return
     */
    @PostMapping("/api/followering/add/")
    public Result addUsersFollowering(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
            logger.info("************  request data " + args);
            String tel = String.valueOf(args.getJSONObject("data").get("tel"));
            int star_user_id = userService.getUserInfoBytelInt(tel);
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            if (star_user_id == 0) {
                return ResultGenerator.genFailResult("用户不存在。");
            }else {
                UsersStarInfo usersStarInfo = new UsersStarInfo();
                usersStarInfo.setStar_user_id(star_user_id);
                usersStarInfo.setUser_id(user_id);

                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                System.out.println(dateFormat.format(date));
                usersStarInfo.setStar_create_time(dateFormat.format(date));
                logger.info("userStarInfo "+usersStarInfo.toString());
                usersStarService.addUserStar(usersStarInfo);
                return ResultGenerator.genSuccessResult();
            }

        } else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }
    }


    /**
     * del followers
     * @param args
     * @return
     */
    @PostMapping("/api/followers/del/")
    public Result delUsersFollowes(@RequestBody JSONObject args) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
        if (check_token) {
//            int star_user_id = userService.getUserInfoBytelInt(String.valueOf(args.getJSONObject("data").get("tel")));
            int star_user_id = (Integer) args.getJSONObject("data").get("star_user_id");
            int user_id = userService.getUserInfoByToken(String.valueOf(args.getJSONObject("data").get("token")));
            if (star_user_id == 0) {
                return ResultGenerator.genFailResult("用户不存在。");
            }else {
                usersStarService.delUserStar(star_user_id,user_id);
                return ResultGenerator.genSuccessResult();
            }
        } else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }
    }

}

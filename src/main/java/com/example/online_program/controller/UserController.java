package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.service.ProjectManageService;
import com.example.online_program.service.SimpleCodeService;
import com.example.online_program.service.UserService;
import com.example.online_program.service.UsersStarService;
import com.example.online_program.utils.Utils;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultCode;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.description;

/**
 * @Created by  qiao
 * @date 18-12-9 下午2:25
 * @Description: 用户管理
 */

@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    ProjectManageService projectManageService;

    @Autowired
    UsersStarService usersStarService;

    @Autowired
    SimpleCodeService simpleCodeService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登录
     *
     * @param args
     * @return
     */
    @PostMapping("/api/user/login/")
    public Result UserLogin(@RequestBody JSONObject args) {

        Userinfo userinfo = JSON.parseObject(String.valueOf(args.getJSONObject("data")), Userinfo.class);
        Result result = userService.checkNameAndPwd(userinfo.getTel(), userinfo.getPwd());

        if (result.getCode() == 200) {
            Map<String, Object> return_data = new HashMap<String, Object>();
            Userinfo userinfo1 = (Userinfo) result.getData();
            return_data.put("token",userinfo1.getToken());
            return_data.put("id",userinfo1.getId());
            return_data.put("userName",userinfo1.getUserName());
            return_data.put("age",userinfo1.getAge());
            return_data.put("birthday",userinfo1.getBirthday());
            return_data.put("description",userinfo1.getDescription());
            return_data.put("tel",userinfo1.getTel());
            return_data.put("email",userinfo1.getEmail());
            return_data.put("is_super",userinfo1.getIs_super());
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +return_data);
            long repositories_count = projectManageService.getProjectCount(1,1000,userinfo1.getId(),userinfo1.getIs_super());
            Integer  following_count=   usersStarService.getUserStarsCount(userinfo1.getId(),1,10000);
            Integer  follower_count=   usersStarService.getUserFollowersCount(userinfo1.getId(),1,10000);
            Integer  code_count=   simpleCodeService.getSimpleCodeCount(userinfo1.getId());

            return_data.put("repositories_count",repositories_count);
            return_data.put("following_count",following_count);
            return_data.put("follower_count",follower_count);
            return_data.put("code_count",code_count);


            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +return_data);
            return ResultGenerator.genSuccessResult(return_data);
        } else {
            String result_data = "用户名或密码错误";
            logger.info("请求参数：" + String.valueOf(args.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED,result_data);
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
        userinfo.setIs_active(true);
        Userinfo user = userService.insertUserInfo(userinfo);
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/user/edit/")
    public Result UserEdit(@RequestBody JSONObject jsonParam) {
        //TODO edit
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));

        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
            String user_name = (String) jsonParam.getJSONObject("data").get("user_name");
            String tel = (String) jsonParam.getJSONObject("data").get("tel");
            String email = (String) jsonParam.getJSONObject("data").get("email");
            String birthday = (String) jsonParam.getJSONObject("data").get("birthday");
            String description = (String) jsonParam.getJSONObject("data").get("description");
            Boolean sex = (Boolean) jsonParam.getJSONObject("data").get("sex");
            Result result = userService.editUserinfo( user_name, tel, email, sex, birthday, description, user_id);
            return result;
        }
        else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(jsonParam.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }
    }


    /**
     * 用户列表模块
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/user/list/simple/")
    public Result QueryByPageUserListSimple(@RequestBody JSONObject jsonParam) {
        Integer page = (Integer) jsonParam.getJSONObject("data").get("page");
        Integer size = (Integer) jsonParam.getJSONObject("data").get("size");
        System.out.println(jsonParam.getJSONObject("data"));
//      UserController page = JSON.parseObject(String.valueOf(jsonParam.getJSONObject("data")), UserController.class);
        Result result = userService.getUserList(page,size);
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
     * 通过电话号码查询用户id
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/user/info/by/tel/")
    public Result UserInfoByTel(@RequestBody JSONObject jsonParam) {
        Result result = userService.getUserInfoBytel(String.valueOf(jsonParam.getJSONObject("data").get("tel")));
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

package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.entity.SimpleCodeInfo;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.service.ProjectManageService;
import com.example.online_program.service.UserService;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectManageController.java
 * @package_name: online_program
 * @Description: 对用户所拥有项目的操作
 */
@RestController
public class ProjectManageController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    ProjectManageService projectManageService;
    private Logger logger = LoggerFactory.getLogger(ProjectManageController.class);

    /**
     * 创建项目
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/projectManage/create/")
    public Result ProjectCreate(@RequestBody JSONObject jsonParam) {
        ProjectInfo projectInfo = JSON.parseObject(String.valueOf(jsonParam), ProjectInfo.class);

        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
            if (user_id == 0) {
                return ResultGenerator.genFailResult("用户不存在。");
            }else {
                // TODO 创建项目成员列表 获取项目成员列表id

                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                System.out.println(dateFormat.format(date));
                projectInfo.setProj_create_time(dateFormat.format(date));
                logger.info("projectInfo "+projectInfo.toString());
                projectManageService.addProject(projectInfo);
                return ResultGenerator.genSuccessResult();
            }
        } else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(jsonParam.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }
    }

    /**
     * 获取项目列表
     *
     * TODO 添加全局筛选仓库模块 该功能不能查到自己的项目
     *
     * @param pageSize
     * @param page
     * @return
     */
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


    /**
     * 收藏项目功能
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/projectManage/collect/")
    public Result ProjectCollect(@RequestBody JSONObject jsonParam) {
        //TODO  验证用户信息
        //      获取项目id
        //      添加项目列表中  注意：非项目创建者，智能查看，不能修改

        Result result = ResultGenerator.genSuccessResult();
        return result;
    }


}

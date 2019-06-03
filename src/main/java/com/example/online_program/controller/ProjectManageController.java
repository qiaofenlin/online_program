package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.entity.ProjectMemberInfo;
import com.example.online_program.service.ProjectManageService;
import com.example.online_program.service.ProjectMemberService;
import com.example.online_program.service.UserService;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    ProjectMemberService projectMemberService;

    @Value("${user-project.path}")
    private String path;


    /**
     * 创建项目
     * @param jsonParam
     * @return
     */
    @PostMapping("/api/projectManage/create/")
    public Result ProjectCreate(@RequestBody JSONObject jsonParam) {
        ProjectInfo projectInfo = JSON.parseObject(String.valueOf(jsonParam.getJSONObject("data")), ProjectInfo.class);
        logger.info("\n============\n projectInfo " + projectInfo.toString());
        String token = String.valueOf(jsonParam.getJSONObject("data").get("token"));
        logger.info("\n============\n token " + token);
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
            if (user_id == 0) {
                return ResultGenerator.genFailResult("用户不存在。");
            }else {
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                SimpleDateFormat dateFormat_name= new SimpleDateFormat("yyyyMMdd_hhmmss");
                System.out.println(dateFormat.format(date));
                projectInfo.setProjCreateTime(dateFormat.format(date));
                projectInfo.setProjName(projectInfo.getUserId()+"");
                projectInfo.setProjPath(path+projectInfo.getProjName());
                projectInfo.setIs_active(true);
//                projectInfo.setProj_nick_name(proj_nick_name);
                logger.info("projectInfo "+projectInfo.toString());
                Integer proj_id = projectManageService.addProject(projectInfo);
                ProjectMemberInfo memberInfo = new ProjectMemberInfo();
                memberInfo.setMember_create_time(dateFormat.format(date));
                memberInfo.setUser_id(projectInfo.getUserId());
                memberInfo.setProj_id(proj_id);
                projectMemberService.addProjectMember(memberInfo);
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
     * @return
     */
    @PostMapping("/api/projectManage/list/")
    public Result ProjectQueryList(@RequestBody JSONObject jsonParam) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
        Boolean is_super = userService.getIsSuperByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
        if (check_token) {
            int user_id = userService.getUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));
            Integer page = (Integer) jsonParam.getJSONObject("data").get("page");
            Integer size = (Integer) jsonParam.getJSONObject("data").get("size");

            Result result = projectManageService.getProjectList(page,size,user_id,is_super);

            return result;
        } else {
            String result_data = "验证信息错误";
            logger.info("请求参数：" + String.valueOf(jsonParam.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genSuccessResult();
        }



    }


    @PostMapping("/api/projectManage/edit/")
    public Result ProjectEdit(@RequestBody JSONObject jsonParam) {
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @PostMapping("/api/projectManage/delete/")
    public Result ProjectDelete(@RequestBody JSONObject jsonParam) {
        Boolean check_token = userService.checkUserInfoByToken(String.valueOf(jsonParam.getJSONObject("data").get("token")));

        if (check_token) {
            Integer proj_id = (Integer) jsonParam.getJSONObject("data").get("proj_id");

            projectManageService.updateProjIsActive(proj_id,false);
            Result result = ResultGenerator.genSuccessResult();
            return result;
        }else {
            String result_data = "验证信息错误。";
            logger.info("请求参数：" + String.valueOf(jsonParam.getJSONObject("data"))+ " result:" +result_data);
            return ResultGenerator.genFailResult(result_data);
        }


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

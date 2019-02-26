package com.example.online_program.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import com.example.online_program.service.UserService;
import com.example.online_program.utils.Layui;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * @Created by  qiao
 * @date 18-10-20 下午3:43
 */

@RestController
public class helloController {
    @RequestMapping("/hello")
    public Object hello() {
        return "hello springboot~~~";
    }

    public Userinfo getUser() {
        Userinfo userinfo = new Userinfo();
        System.out.println();
        return userinfo;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/aaa")
    public String index() {

//        Layui.data(1,);
        return "login";
    }

    @PostMapping(value = "/api/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map) {

        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            return "index";
        } else {
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }

    @Cacheable(cacheNames = "user_info")
    @GetMapping("/user/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        Optional<Userinfo> userinfo = userRepository.findById(id);
        Result result= ResultGenerator.genSuccessResult(userinfo);
        return result;
    }

    @PostMapping("/user/")
    public Result insertUser(@RequestBody JSONObject jsonParam) {
        System.out.println("**********************************************" + jsonParam.toJSONString());
        // TODO todo pojo
        Userinfo userinfo = JSON.parseObject(String.valueOf(jsonParam), Userinfo.class);
        System.out.println("***********************************************toString()"+userinfo.toString());
        Userinfo user = userRepository.save(userinfo);
        Result result = ResultGenerator.genSuccessResult(user.toString());
        return result;
    }



    @GetMapping("/user/one/{id}")
    public Result getUserOne(@PathVariable("id") Integer id) {
//        String userinfo1 = userService.getUserInfobyId(id);
        Optional<Userinfo> userinfo1 = userService.getUserInfobyId(id);
        Result result = ResultGenerator.genSuccessResult(userinfo1.get());
        return result;
    }


}


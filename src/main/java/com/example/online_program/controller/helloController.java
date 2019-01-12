package com.example.online_program.controller;

import com.example.online_program.entity.Userinfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


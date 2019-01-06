package com.example.online_program.controller;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Created by  qiao
 * @date 18-12-9 下午2:25
 */

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping(value = "/usr/login")
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

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value="page",defaultValue = "0") Integer page, @RequestParam(value="size",defaultValue = "5") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<Userinfo> userList = userRepository.findList(pageable);
        model.addAttribute("users",userList);
        return "user/list";
    }
}

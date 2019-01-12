package com.example.online_program.controller;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * @Created by  qiao
 * @date 18-12-9 下午2:25
 */

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
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

//    @RequestMapping("/list")
//    public Page<Userinfo> list(Model model, @RequestParam(value="page",defaultValue = "0") Integer page, @RequestParam(value="size",defaultValue = "5") Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(page, size, sort);
//        Page<Userinfo> userList = userRepository.findList(pageable);
////        model.addAttribute("users",userList);
////        return "user/list";
//        return userList;
//
//    }


    @GetMapping("/user/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        Optional<Userinfo> userinfo = userRepository.findById(id);
         Result result= ResultGenerator.genSuccessResult(userinfo);
        return result;
    }

    @GetMapping("/user/")
    public Userinfo insertUser(Userinfo userinfo) {

        Userinfo user = userRepository.save(userinfo);
        return user;
    }
}

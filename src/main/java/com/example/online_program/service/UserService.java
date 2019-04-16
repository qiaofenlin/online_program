package com.example.online_program.service;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


/**
 * @Author: qfl
 * @Date: 19-1-13 下午2:50
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);


    @Cacheable(cacheNames = "user_info" ,key = "#id",unless = "#result eq null")
    public Optional  getUserInfobyId(Integer id) {
        System.out.println("*********************************** execute  ");
        Optional<Userinfo> userinfo = userRepository.findById(id);
        return userinfo;
    }


//    @Cacheable(cacheNames = "user_info" ,key = "#id",unless = "#result eq null")
    public Userinfo  insertUserInfo(Userinfo userinfo) {
        logger.debug("[insert user_info] " + userinfo.toString());
        Userinfo user = userRepository.save(userinfo);
        return user;
    }

    public boolean checkNameAndPwd(String username,String password) {
        logger.debug(username +": \t" + password);
//        logger.debug(args.getData()['user_name']);
        Optional<Userinfo> user = userRepository.findByUserName(username);
        if(user.get().getPwd().equals(password)){
            System.out.println(" \n");
            return true;

        }else {
            System.out.println(" \n");
            return false;

        }
    }

}

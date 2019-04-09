package com.example.online_program.service;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Cacheable(cacheNames = "user_info" ,key = "#id",unless = "#result eq null")
    public Optional  getUserInfobyId(Integer id) {
        System.out.println("*********************************** execute  ");
        Optional<Userinfo> userinfo = userRepository.findById(id);
        return userinfo;
    }

//    public
}

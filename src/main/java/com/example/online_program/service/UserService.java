package com.example.online_program.service;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @Author: qfl
 * @Date: 19-1-13 下午2:50
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Cacheable
    public Userinfo getUserInfobyId(Integer id) {
        return userRepository.getOne(id);
    }
}

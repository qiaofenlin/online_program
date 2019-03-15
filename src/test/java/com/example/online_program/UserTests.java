package com.example.online_program;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import com.example.online_program.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @program: online_program
 * @description: 用户模块测试用例
 * @author: qfl
 * @create: 2019-03-15 16:21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    @Resource
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserService.class);


    @Test
    public void contextLoads() {

//        Userinfo user = userRepository.getByUserNameExistsAndPwdExists("乔风鳞", "1234569");
//        Optional<Userinfo> user = userRepository.findAllById(1);
//        logger.debug("************ user "+user.toString());


        // TODO 测试执行sql 同时获取部分值。
        Optional<String> user = userRepository.getByUserNameExistsAndPwdExists("乔风鳞", "1234569");
        logger.info("************ user "+user.get());



    }

    /**
     * JPA TEST
     */
//    @Test
//    public void user_jpa() {
//        Optional<Userinfo> user = userRepository.getByUserName("乔风鳞");
//        logger.debug("******************************"+user.toString());
//
//    }

    @Test
    public void user_jpa2() {
        Optional<Userinfo> user = userRepository.findByUserName("乔风鳞");
        logger.debug("******************************"+user.get());

    }



}
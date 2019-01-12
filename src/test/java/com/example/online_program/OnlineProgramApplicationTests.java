package com.example.online_program;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.pojo_fake.DemoJSONResult;
import com.example.online_program.pojo_fake.User;
import com.example.online_program.repository.UserRepository;
import org.hibernate.annotations.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineProgramApplicationTests {

    @Resource
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {

        logger.trace("tracre");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }

    @Test
    public void test(){
//        Userinfo userinfo = userRepository.findAllById(1).get();
//        System.out.println("test start");
//        System.out.println(userinfo);
//        Date date = new Date();
//        System.out.println("*****************"+ date);
//        User user = new User("a", 1, date, "b");
////        DemoJSONResult demoJSONResult = new DemoJSONResult();
//        DemoJSONResult result = DemoJSONResult.format("{'a':'123'}");
//        System.out.println("******************************result| "+result);
    }

    /**
     * 获取当前时间
     */
    @Test
    public void Data() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(new Date().getTime());
    }


}

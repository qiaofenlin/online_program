package com.example.online_program;

import com.alibaba.fastjson.JSON;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineProgramApplicationTests {

    @Resource
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作字符串

    @Autowired
    RedisTemplate redisTemplate;  //操作k-v

    @Autowired
    RedisTemplate<String,Object> redisTemplateObj; // 自定义 object

//    @Qualifier("UserInforedisTemplate")
//    @Autowired
//    RedisTemplate<Object,Userinfo> UserInforedisTemplate;

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
//        stringRedisTemplate.opsForValue().append("msg","hello");//str
        String str = stringRedisTemplate.opsForValue().get("msg");  // TODO opt string
        System.out.println("****************************************************** | "+str);
//        stringRedisTemplate.opsForSet()// set
    }

    @Test
    public void test01() {
        Optional<Userinfo> userinfo = userRepository.findById(1);
        Userinfo userinfo1 = (Userinfo)userinfo.get();
        System.out.println("*******************************Optional.of(userinfo);  |  "+userinfo1.getUserName()); //TODO 获取对象
//        System.out.println("********************************"+userinfo.toString());
        // 保存对象 使用java序列化机制，序列化后的数据保存到redis中
//        redisTemplate.opsForValue().set("user_1", userinfo.toString());
//        String userinfo1 = (String) redisTemplate.opsForValue().get("user_1");
//        System.out.println("**************************************** --- ===="+userinfo1);
    }




    /**
     * TODO test redisTemplateObj
     */
    @Test
    public void obj_to_redis() {
        System.out.println("********************************************");
        Optional<Userinfo> userinfo = userRepository.findById(1);
        Userinfo userinfo1 = (Userinfo) userinfo.get();

        redisTemplateObj.opsForValue().set("123", userinfo1);
        Userinfo userinfo2 = JSON.parseObject(String.valueOf(redisTemplateObj.opsForValue().get("123")), Userinfo.class);
        System.out.println("#########################################"+userinfo2.toString());

//        UserInforedisTemplate.opsForValue().set("123", userinfo1);
//        Userinfo userinfo2 = JSON.parseObject(String.valueOf(UserInforedisTemplate.opsForValue().get("123")), Userinfo.class);
//        System.out.println("#########################################"+userinfo2.toString());
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


//    public class CacheTest {
//        /**
//         * 缓存测试方法延时两秒
//         * @param i
//         * @return
//         */
//
//    }

    @Cacheable(value = "cache_test")
    public String cacheFunction(int i){
        try {
            long time = 2000L;
            System.out.println("11111111111111");
            Thread.sleep(time);
            System.out.println("222222222222222");
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return "success"+ i;
    }

}

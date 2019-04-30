package com.example.online_program.service;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Optional<Userinfo> getUserInfobyId(Integer id) {
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




    public Result getUserList(int page, int size) {
        Specification<Userinfo> spec = new Specification<Userinfo>() {
            /**
             * 多个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<Userinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.isNotNull(root.get("userName"));
            }
        };
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        System.out.println("*********************"+page + "\t"+ size);
        Pageable pageable = PageRequest.of(page-1,size,sort);

        Page<Userinfo> page_user_info = userRepository.findAll(spec,pageable);

        System.out.println("总条数:" + page_user_info.getTotalElements());
        System.out.println("总页数:" + page_user_info.getTotalPages());
        List<Userinfo> list = page_user_info.getContent();
        for (Userinfo userinfo : list) {
            logger.debug("=============== user :"+userinfo);
        }
        Result result = ResultGenerator.genSuccessResult();
        Map<String, Object> user_list = new HashMap<String, Object>();
        user_list.put("user_info_list",list);
        user_list.put("count",page_user_info.getTotalElements());
        user_list.put("all_page",page_user_info.getTotalPages());
        result.setData(user_list);
        return result;
    }


//    public ResultPage<Userinfo> queryByPage(ResultPage<Userinfo> result) {
//
//        PageRequest request = PageRequest.of(result.getPage() - 1, result.getLimit());//使用PageRequest 设置起始页与页数大小
//        Example<Userinfo> ex = Example.of(result.getExample());//利用jpa中的方法将条件转为Example类型
//        Page<Userinfo> all = userRepository.findAll(ex, request);//调用jpa中的实现方法返回数据
//        result.setData(all.getContent());
//        result.setCount((int) all.getTotalElements());
//        return result;
//
//    }




}

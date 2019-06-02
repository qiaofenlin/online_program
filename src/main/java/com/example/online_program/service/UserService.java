package com.example.online_program.service;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.UserRepository;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultCode;
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

import javax.persistence.criteria.*;
import java.util.*;


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


    @Cacheable(cacheNames = "user_info", key = "#id", unless = "#result eq null")
    public Optional<Userinfo> getUserInfobyId(Integer id) {
        System.out.println("*********************************** execute  ");
        Optional<Userinfo> userinfo = userRepository.findById(id);
        return userinfo;
    }


    //    @Cacheable(cacheNames = "user_info" ,key = "#id",unless = "#result eq null")
    public Userinfo insertUserInfo(Userinfo userinfo) {
        logger.debug("[insert user_info] " + userinfo.toString());
        Userinfo user = userRepository.save(userinfo);
        return user;
    }

    public Result checkNameAndPwd(String tel, String password) {
        logger.debug(tel + ": \t" + password);
        Optional<Userinfo> user = userRepository.findByTel(tel);

        if (user.get().getPwd().equals(password)) {
//            String token = user.get().getToken();
            Result result = ResultGenerator.genSuccessResult(user.get());
            return result;
        } else {
            Result result = ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED);
            return result;

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
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Userinfo> page_user_info = userRepository.findAll(spec, pageable);
        logger.info("总条数:" + page_user_info.getTotalElements() + "\t总页数:" + page_user_info.getTotalPages());
        List<Userinfo> list = page_user_info.getContent();
        Result result = ResultGenerator.genSuccessResult();
        Map<String, Object> user_list = new HashMap<String, Object>();
        user_list.put("user_info_list", list);
        user_list.put("count", page_user_info.getTotalElements());
        user_list.put("all_page", page_user_info.getTotalPages());
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

    public int getUserInfoByToken(String token) {
        Optional<Userinfo> user = userRepository.findAllByToken(token);
        Integer user_id = user.map(Userinfo::getId).orElse(0);
        return user_id;
    }

    public Boolean getIsSuperByToken(String token) {
        Optional<Userinfo> user = userRepository.findAllByToken(token);
        Boolean is_super = user.map(Userinfo::getIs_super).orElse(false);
        return is_super;
    }

    public Boolean checkUserInfoByToken(String token) {
        Boolean existsByToken = userRepository.existsByToken(token);
        return existsByToken;

    }

    public Result getUserListByUserId(List<Integer> user_id_list,int page, int size) {
        Specification<Userinfo> spec = new Specification<Userinfo>() {
            /**
             * 多个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<Userinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (user_id_list != null && user_id_list.size() > 0) {
                    CriteriaBuilder.In<Object> in = cb.in(root.get("id"));
                    for (Integer id : user_id_list) {
                        in.value(id);
                    }
                    list.add(in);
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        List<Userinfo> list;
        List<Userinfo> result_safe_user_info = new ArrayList<>();
        Map<String, Object> user_list = new HashMap<String, Object>();
        if (user_id_list.size() >0){
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            Pageable pageable = PageRequest.of(page - 1, size, sort);
            Page<Userinfo> page_user_info = userRepository.findAll(spec, pageable);
            logger.info("总条数:" + page_user_info.getTotalElements() + "\t总页数:" + page_user_info.getTotalPages());
            list = page_user_info.getContent();
            user_list.put("count", page_user_info.getTotalElements());
            user_list.put("all_page", page_user_info.getTotalPages());
            for (Userinfo userinfo : list) {
                result_safe_user_info.add(Userinfo.set_safe_user_info(userinfo));
            }
        }
        Result result = ResultGenerator.genSuccessResult();
        user_list.put("user_info_list", result_safe_user_info);
        result.setData(user_list);
        return result;
    }

    public Result getUserInfoBytel(String tel) {
        Optional<Userinfo> user = userRepository.findByTel(tel);
        System.out.println("****************************user.get() "+user);
        Integer user_id = user.map(Userinfo::getId).orElse(0);
        System.out.println("******************** user_id "+user_id);
        Map<String, Object> result_user_id = new HashMap<String, Object>();
        Result result = ResultGenerator.genSuccessResult();
        result_user_id.put("user_id", user_id);
        result.setData(result_user_id);

        return result;

    }

    public int getUserInfoBytelInt(String tel) {
        Optional<Userinfo> user = userRepository.findByTel(tel);
        System.out.println("****************************user.get() "+user);
        Integer user_id = user.map(Userinfo::getId).orElse(0);
        System.out.println("******************** user_id "+user_id);
        return user_id;

    }

    /**
     * TODO
     * @param userinfo
     * @return
     */
    public Result updateUserinfo(Userinfo userinfo) {
        Result result = ResultGenerator.genSuccessResult();
        userRepository.updateOne("123","123456");
        return result;
    }

}

package com.example.online_program.service;

import com.example.online_program.entity.UsersStarInfo;
import com.example.online_program.repository.UsersStarRepository;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersStarService {

    @Autowired
    UsersStarRepository usersStarRepository;

    Logger logger = LoggerFactory.getLogger(UsersStarService.class);


    public List<Integer> getUserStarsList(int user_id,int page,int size) {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
            /**
             * 单个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<UsersStarInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user_id"), user_id);

            }
        };
        List<UsersStarInfo> list = usersStarRepository.findAll(spec);
        List<Integer> user_id_list = new ArrayList<>();
        for (UsersStarInfo user_star_info : list) {
            user_id_list.add(user_star_info.getStar_user_id());
        }
//        Map<String, Object> user_list = new HashMap<String, Object>();
//        user_list.put("user_info_list", list);
//        user_list.put("count", page_user_info.getTotalElements());
//        user_list.put("all_page", page_user_info.getTotalPages());
//        result.setData(user_list);
        return user_id_list;
    }

}

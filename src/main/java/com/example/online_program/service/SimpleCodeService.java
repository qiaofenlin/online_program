package com.example.online_program.service;

import com.example.online_program.entity.SimpleCodeInfo;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.entity.UsersStarInfo;
import com.example.online_program.repository.SimpleCodeRepository;
import com.example.online_program.repository.UsersStarRepository;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimpleCodeService {

    @Autowired
    SimpleCodeRepository simpleCodeRepository;

    Logger logger = LoggerFactory.getLogger(SimpleCodeService.class);

    /**
     * Following list
     * @param user_id
     * @return
     */
    public Result getSimpleCodeList(int user_id) {
        Specification<SimpleCodeInfo> spec = new Specification<SimpleCodeInfo>() {
            @Override
            public Predicate toPredicate(Root<SimpleCodeInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user_id"), user_id);

            }
        };
        List<SimpleCodeInfo> list = simpleCodeRepository.findAll(spec);
        Result result = ResultGenerator.genSuccessResult();
        Map<String, Object> simple_codeList = new HashMap<String, Object>();
        simple_codeList.put("code_info_list", list);
        result.setData(list);
        logger.info("[getSimpleCodeList userid "+user_id +" simple_codeList len "+list.size()+"] ");

        return result;
    }


    public void addSimpleCode(SimpleCodeInfo simpleCodeInfo) {
        SimpleCodeInfo result = simpleCodeRepository.save(simpleCodeInfo);
        logger.info(result.toString());
    }

    public void delUserStar(Integer code_id,Integer user_id) {
        int result = simpleCodeRepository.deleteByUser_idAndAndStar_user_id(code_id,user_id);
        logger.info("[del user_id "+user_id+" | code_id "+code_id+" | del result " +result);
    }

}

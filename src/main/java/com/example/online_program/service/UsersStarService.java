package com.example.online_program.service;

import com.example.online_program.entity.UsersStarInfo;
import com.example.online_program.repository.UsersStarRepository;
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

    /**
     * Following list
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    public List<Integer> getUserStarsList(int user_id,int page,int size) {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
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

        return user_id_list;
    }

    /**
     * Following count
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    public Integer getUserStarsCount(int user_id,int page,int size) {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
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

        return user_id_list.size();
    }

    /**
     * Followers list
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    public List<Integer> getUserFollowersList(int user_id,int page,int size) {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
            @Override
            public Predicate toPredicate(Root<UsersStarInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("star_user_id"), user_id);

            }
        };
        List<UsersStarInfo> list = usersStarRepository.findAll(spec);
        List<Integer> user_id_list = new ArrayList<>();
        for (UsersStarInfo user_star_info : list) {
            user_id_list.add(user_star_info.getUser_id());
        }
        return user_id_list;
    }
    /**
     * Followers count
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    public int getUserFollowersCount(int user_id, int page, int size) {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
            @Override
            public Predicate toPredicate(Root<UsersStarInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("star_user_id"), user_id);

            }
        };
        List<UsersStarInfo> list = usersStarRepository.findAll(spec);
        List<Integer> user_id_list = new ArrayList<>();
        for (UsersStarInfo user_star_info : list) {
            user_id_list.add(user_star_info.getUser_id());
        }
        return user_id_list.size();
    }

    public void addUserStar(UsersStarInfo usersStarInfo) {
        UsersStarInfo result = usersStarRepository.save(usersStarInfo);
        logger.info(result.toString());
    }

    public void delUserStar(Integer star_user_id,Integer user_id) {
        int result = usersStarRepository.deleteByUser_idAndAndStar_user_id(star_user_id,user_id);
        logger.info("[del user_id "+user_id+" | star_user_id "+star_user_id+" | del result " +result);
    }

}

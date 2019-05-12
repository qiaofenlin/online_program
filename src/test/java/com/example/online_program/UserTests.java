package com.example.online_program;

import com.example.online_program.entity.Userinfo;
import com.example.online_program.entity.UsersStarInfo;
import com.example.online_program.repository.UserRepository;
import com.example.online_program.repository.UsersStarRepository;
import com.example.online_program.service.UserService;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Resource
    private UsersStarRepository usersStarRepository;
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
    @Test
    public void date_test() {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(date));

    }

    @Test
    public void user_jpa2() {
//        Optional<Userinfo> user = userRepository.findByTel("18322693235");
//        logger.info("******************************"+user.get().getPwd());
        String uuid = UUID.randomUUID().toString();

        System.out.println(uuid);   //打印UUID

        uuid = uuid.replace("-", "");

        System.out.println(uuid);

    }

    /**
     * 排序测试
     */
    @Test
    public void user_jpa_sort() {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Userinfo> list  =userRepository.findAll(sort);
        for (Userinfo userinfo : list) {
            System.out.println(userinfo);
        }
    }


    /**
     * 分页测试
     */
    @Test
    public void user_jpa_page() {
        Pageable pageable =PageRequest.of(1,2);
        Page<Userinfo> page = userRepository.findAll(pageable);
        logger.info("总条数:" + page.getTotalElements() + "\t总页数:" + page.getTotalPages());
        List<Userinfo> list = page.getContent();
        for (Userinfo userinfo : list) {
            System.out.println(userinfo);
        }
    }

    public void user_opt() {
//        List<Userinfo> user_list = userRepository.findAll();
//        logger.debug("*****************************\n" + user_list.toString());
//        Pageable pageable = PageRequest.of(1, 10,Sort.Direction.DESC,"id");
//        Page<Userinfo> page = userRepository.findAll(pageable);
//        System.out.println(page.getNumber());  //当前页start
//        System.out.println(page.getNumberOfElements());  //当前页start
//        System.out.println(page.getSize());   //每页数量size
//        System.out.println(page.getTotalElements());  //总数量
//        System.out.println(page.getTotalPages());    //总页数
//        System.out.println(page.getContent());    //总内容
        List<Userinfo> userinfos = userRepository.findAllByIdPage(1, 3);
        logger.debug("**************************\n ", userinfos.toString());

    }




    /**
     * 排序加分页测试
     */
    @Test
    public void user_jpa_sort_page() {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable =PageRequest.of(1,10,sort);
        Page<Userinfo> page = userRepository.findAll(pageable);
        List<Userinfo> list = page.getContent();
        for (Userinfo userinfo : list) {
            System.out.println("************************" + userinfo.toString());
        }
        logger.info("总条数:" + page.getTotalElements() + "\t总页数:" + page.getTotalPages());


    }

    /**
     * JPASpecificationExecutor接口
     * 多条件查询支持，可以在查询中添加和分页
     */
    @Test
    public void user_jpa_JpaSpecificationExecutor() {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
            /**
             * 单个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<UsersStarInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate pre = criteriaBuilder.equal(root.get("userName"),"乔风鳞");
                Predicate pre =criteriaBuilder.isNotNull(root.get("user_id"));
                return pre;
            }
        };
        List<UsersStarInfo> list = usersStarRepository.findAll(spec);

        for (UsersStarInfo userinfo : list) {
            System.out.println("user :"+userinfo.toString());
        }
    }


    /**
     * JPASpecificationExecutor接口
     * 多条件查询支持，可以在查询中添加和分页
     */
    @Test
    public void user_jpa_JpaSpecificationExecutor_more() {
        Specification<Userinfo> spec = new Specification<Userinfo>() {
            /**
             * 多个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<Userinfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("userName"), "乔风鳞"));
                list.add(criteriaBuilder.equal(root.get("age"), 18));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        List<Userinfo> list = userRepository.findAll(spec);
        for (Userinfo userinfo : list) {
            System.out.println("user :"+userinfo);
        }
    }

    @Test
    public void user_jpa_JpaSpecificationExecutor_more2() {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
            /**
             * 多个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<UsersStarInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                return cb.or(cb.and(cb.equal(root.get("userName"), "乔风鳞"),cb.equal(root.get("age"), 18)),cb.equal(root.get("id"),4));
                return cb.isNotNull(root.get("user_id"));

            }
        };
        Sort sort = new Sort(Sort.Direction.ASC,"star_id");
        Pageable pageable =PageRequest.of(0,3,sort);
        Page<UsersStarInfo> page = usersStarRepository.findAll(spec,pageable);
        logger.info("总条数:" + page.getTotalElements() + "\t总页数:" + page.getTotalPages());
    }

    /**
     * 排序测试
     */
    @Test
    public void get_user_by_token() {
//        Boolean user = userRepository.existsByToken("e1ffe11015e74cda87e7e8e9b36c18a9");
        Optional<Userinfo> user = userRepository.findByTel("1832269323511");
        System.out.println("****************************user.get() "+user);
//        Optional.ofNullable(user).map(Userinfo::getId).orElse("no name")
        Integer user_id = user.map(Userinfo::getId).orElse(0);
//        Integer user_id = user.get().getId();
        System.out.println("******************** user_id "+user_id);
    }
    /**
     * 排序测试
     */
    @Test
    public void get_user_by_token1() {
        Specification<UsersStarInfo> spec = new Specification<UsersStarInfo>() {
            /**
             * 多个条件的查询
             */
            @Override
            public Predicate toPredicate(Root<UsersStarInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.isNotNull(root.get("user_id"));
//                return cb.equal(root.get("user_id"), 6);
            }
        };
        Sort sort = new Sort(Sort.Direction.ASC, "star_create_time");
        System.out.println("*********************" + 1 + "\t" + 10);
        Pageable pageable = PageRequest.of(0, 10, sort);

        Page<UsersStarInfo> page_user_info = usersStarRepository.findAll(spec, pageable);
        logger.info("总条数:" + page_user_info.getTotalElements() + "\t总页数:" + page_user_info.getTotalPages());
        List<UsersStarInfo> list = page_user_info.getContent();
        Result result = ResultGenerator.genSuccessResult();

    }

    @Test
    public void del_opt() {
        int a = usersStarRepository.deleteByUser_idAndAndStar_user_id(6, 8);
        System.out.println(a);
    }
}

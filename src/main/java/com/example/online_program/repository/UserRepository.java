package com.example.online_program.repository;

import com.example.online_program.entity.Userinfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @Created by  qiao
 * @date 18-12-9 下午1:55
 */

public interface UserRepository extends JpaRepository<Userinfo, Integer>, JpaSpecificationExecutor<Userinfo> {

//    @Query("select u from Userinfo u")
//    Page<Userinfo> findList(Pageable pageable);
//
//
    Optional<Userinfo> findAllById(int id);
    Optional<Userinfo> findAllByToken(String token);
    Boolean existsByToken(String token);
//
//    //简单自定义查询
//    Userinfo findAllByUserName(String userName);
//
//    void deleteById(Long id);
    @Query("select u.pwd from Userinfo u where u.userName=:username and u.pwd=:pwd")
    Optional<String > getByUserNameExistsAndPwdExists(@Param("username") String username, @Param("pwd") String password);

    Optional<Userinfo> findByTel(String tel);

    @Query(value = "select * from userinfo  where id > limit ?",nativeQuery = true)
    List<Userinfo> findAllByIdPage(Integer start_page, Integer end_page);

//    Optional<Userinfo> findByIdBetween(@Param("start_page") int start_page, @Param("end_page") int end_page);

    @Query("update  Userinfo u set u.userName = :username where u.userName=:username and u.pwd=:pwd")
    void updateOne(@Param("username") String username, @Param("pwd") String password);




}


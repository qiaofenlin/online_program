package com.example.online_program.repository;

import com.example.online_program.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by  qiao
 * @date 18-12-9 下午1:55
 */

public interface UserRepository extends JpaRepository<Userinfo, Integer> {

//    @Query("select u from Userinfo u")
//    Page<Userinfo> findList(Pageable pageable);
//
//
//    Optional<Userinfo> findAllById(Long id);
//
//    //简单自定义查询
//    Userinfo findAllByUserName(String userName);
//
//    void deleteById(Long id);
}

/**
 * JPA
 *
 *
 */

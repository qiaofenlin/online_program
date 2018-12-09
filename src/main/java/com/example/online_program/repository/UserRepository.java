package com.example.online_program.repository;

import com.example.online_program.entity.Userinfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Created by  qiao
 * @date 18-12-9 下午1:55
 */

public interface UserRepository extends CrudRepository<Userinfo, Long> {

    @Query("select u from Userinfo u")
    Page<Userinfo> findList(Pageable pageable);


    Optional<Userinfo> findAllById(Long id);

    //简单自定义查询
    Userinfo findAllByUserName(String userName);

    void deleteById(Long id);
}

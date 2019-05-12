package com.example.online_program.repository;

import com.example.online_program.entity.SimpleCodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Created by  qiao
 * @date 18-12-9 下午1:55
 */

public interface SimpleCodeRepository extends JpaRepository<SimpleCodeInfo, Integer>, JpaSpecificationExecutor<SimpleCodeInfo> {

    @Modifying
    @Transactional
    @Query("delete from SimpleCodeInfo code where code.code_id =:code_id and code.user_id=:user_id")
    int deleteByUser_idAndAndStar_user_id(@Param("code_id") int code_id, @Param("user_id") int user_id);

}


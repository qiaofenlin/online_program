package com.example.online_program.repository;

import com.example.online_program.entity.UsersStarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersStarRepository extends JpaRepository<UsersStarInfo, Integer>, JpaSpecificationExecutor<UsersStarInfo> {

    @Modifying
    @Transactional
    @Query("delete from UsersStarInfo star where star.star_user_id =:star_user_id and star.user_id=:user_id")
    int deleteByUser_idAndAndStar_user_id(@Param("star_user_id") int star_user_id,@Param("user_id") int user_id);

}

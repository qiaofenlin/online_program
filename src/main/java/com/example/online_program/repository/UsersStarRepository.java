package com.example.online_program.repository;

import com.example.online_program.entity.UsersStarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersStarRepository extends JpaRepository<UsersStarInfo, Integer>, JpaSpecificationExecutor<UsersStarInfo> {

}

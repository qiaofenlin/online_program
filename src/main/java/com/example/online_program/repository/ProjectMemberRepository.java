package com.example.online_program.repository;

import com.example.online_program.entity.ProjectMemberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMemberInfo, Integer> {




}

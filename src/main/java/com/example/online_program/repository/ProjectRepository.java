package com.example.online_program.repository;

import com.example.online_program.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectRepository.java
 * @package_name: online_program
 * @Description:
 */
public interface ProjectRepository extends JpaRepository<Userinfo, Integer> {
}

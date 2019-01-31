package com.example.online_program.repository;

import com.example.online_program.entity.TerminalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: TerminalRepository.java
 * @package_name: online_program
 * @Description:
 */
public interface TerminalRepository extends JpaRepository<TerminalInfo, Integer> {
}

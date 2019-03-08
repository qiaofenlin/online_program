package com.example.online_program.service;

import com.example.online_program.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectManageService.java
 * @package_name: online_program
 * @Description: 对用户项目操作进行提供service
 */

@Service
public class ProjectManageService {
    @Autowired
    ProjectRepository projectRepository;
}
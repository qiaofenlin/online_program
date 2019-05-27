package com.example.online_program.service;

import com.example.online_program.controller.ProjectManageController;
import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ProjectManageService.class);

    public void addProject(ProjectInfo projectInfo) {
            ProjectInfo result = projectRepository.save(projectInfo);
        logger.info(result.toString());
    }
}

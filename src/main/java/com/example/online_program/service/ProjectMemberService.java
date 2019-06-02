package com.example.online_program.service;

import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.entity.ProjectMemberInfo;
import com.example.online_program.repository.ProjectMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectManageService.java
 * @package_name: online_program
 * @Description:
 */

@Service
public class ProjectMemberService {
    @Autowired
    ProjectMemberRepository projectMemberRepository;

    private Logger logger = LoggerFactory.getLogger(ProjectMemberService.class);

    public void addProjectMember(ProjectMemberInfo memberInfo) {
        System.out.println("=====================\n\n"+memberInfo.toString());
        ProjectMemberInfo result = projectMemberRepository.save(memberInfo);
        logger.info(result.toString());
    }
}

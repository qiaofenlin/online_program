package com.example.online_program.service;

import com.example.online_program.controller.ProjectManageController;
import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.entity.Userinfo;
import com.example.online_program.repository.ProjectRepository;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Integer addProject(ProjectInfo projectInfo) {
        System.out.println("=====================\n\n"+projectInfo.toString());
        ProjectInfo result = projectRepository.save(projectInfo);
        logger.info(result.toString());
        return result.getProjId();
    }

    public Result getProjectList(int page, int size,int user_id,Boolean is_super) {
        Sort sort = new Sort(Sort.Direction.ASC, "projId");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ProjectInfo> page_proj_info ;
        if (is_super) {
            Specification<ProjectInfo> spec = new Specification<ProjectInfo>() {
                /**
                 * 管理员
                 */
                @Override
                public Predicate toPredicate(Root<ProjectInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get("is_active"), true);
                }
            };
            page_proj_info =  projectRepository.findAll(spec, pageable);
        }else {
            Specification<ProjectInfo> spec = new Specification<ProjectInfo>() {
                /**
                 * 普通用户
                 */
                @Override
                public Predicate toPredicate(Root<ProjectInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.and(cb.equal(root.get("userId"), user_id),cb.equal(root.get("is_active"), true));
                }
            };
            page_proj_info =  projectRepository.findAll(spec, pageable);
        }
        logger.info("总条数:" + page_proj_info.getTotalElements() + "\t总页数:" + page_proj_info.getTotalPages());
        List<ProjectInfo> list = page_proj_info.getContent();
        Result result = ResultGenerator.genSuccessResult();
        Map<String, Object> proj_list = new HashMap<String, Object>();
        proj_list.put("proj_info_list", list);
        proj_list.put("count", page_proj_info.getTotalElements());
        proj_list.put("all_page", page_proj_info.getTotalPages());
        result.setData(proj_list);
        return result;
    }

    @Transactional
    public void updateProjIsActive(int projId,boolean is_delete) {
        projectRepository.deleteProjectInfo(projId,is_delete);

    }

    @Transactional
    public void renameProj(String proj_nick_name,String proj_name) {
        logger.info("proj_nick_name : "+proj_nick_name+"\t proj_name: "+proj_name);
        projectRepository.updateProjectName(proj_nick_name,proj_name);

    }


}

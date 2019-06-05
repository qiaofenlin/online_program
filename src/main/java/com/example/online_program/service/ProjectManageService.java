package com.example.online_program.service;

import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.entity.ProjectMemberInfo;
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
import java.text.SimpleDateFormat;
import java.util.*;

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


    public Result getfriendProjectList(int page, int size,int user_id,Boolean is_super) {
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
                    return cb.or(cb.and(cb.equal(root.get("userId"), user_id),cb.equal(root.get("is_active"), true),cb.equal(root.get("projStatus"), 0)),cb.and(cb.equal(root.get("userId"), user_id),cb.equal(root.get("is_active"), true),cb.equal(root.get("projStatus"), 2)));
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


    public long getProjectCount(int page, int size, int user_id, Boolean is_super) {
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
        return page_proj_info.getTotalElements();
    }


    @Transactional
    public void updateProjIsActive(int projId,boolean is_delete) {
        projectRepository.deleteProjectInfo(projId,is_delete);

    }

    @Transactional
    public void updateProjStatus(int projId,int proj_status) {
        projectRepository.updateProjectInfo(projId,proj_status);
    }

    @Transactional
    public void renameProj(String proj_nick_name,String proj_name) {
        logger.info("proj_nick_name : "+proj_nick_name+"\t proj_name: "+proj_name);
        projectRepository.updateProjectName(proj_nick_name,proj_name);

    }

    @Transactional
    public void collect_proj(Integer proj_id, Integer type,Integer user_id) {

        Optional<ProjectInfo> projectInfo = projectRepository.findByProjId(proj_id);
        ProjectInfo projectInfo_new = projectInfo.get();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(date));
        projectRepository.insertProj( dateFormat.format(date),projectInfo_new.getProjDescription(),projectInfo_new.getUserId(),projectInfo_new.getProjName(),projectInfo_new.getProjPath()
                ,projectInfo_new.getProj_nick_name(),projectInfo_new.getProjStatus(),type,user_id,true);



    }
    @Transactional
    public String  getProjNickName(int proj_id) {
        Optional<ProjectInfo> projectInfo = projectRepository.findByProjId(proj_id);
        return projectInfo.get().getProj_nick_name();
    }

    @Transactional
    public Set<String> getProjMember(String proj_nick_name) {
        Set<String> user_name = projectRepository.getProjMembers(proj_nick_name);
        System.out.println(user_name.toString());
        return user_name;
    }

}

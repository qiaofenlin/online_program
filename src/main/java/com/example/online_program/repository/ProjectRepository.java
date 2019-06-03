package com.example.online_program.repository;

import com.example.online_program.entity.ProjectInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectRepository.java
 * @package_name: online_program
 * @Description:
 */
//@Mapper
public interface ProjectRepository extends JpaRepository<ProjectInfo, Integer>, JpaSpecificationExecutor<ProjectInfo> {


    @Query(value = "update project_info set is_active=?2 where proj_id=?1 ", nativeQuery = true)
    @Modifying
    public void deleteProjectInfo(int proj_id,Boolean name);


    @Query(value = "update project_info set proj_name=?2 where proj_nick_name=?1 ", nativeQuery = true)
    @Modifying
    public void updateProjectName(String proj_nick_name,String name);


//    @Query("select h.name as name, avg(r.rating) as averageRating  "from Hotel h left outer join h.reviews r  group by h")
//    public void getUserList

}

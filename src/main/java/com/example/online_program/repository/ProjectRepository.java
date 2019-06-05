package com.example.online_program.repository;

import com.example.online_program.entity.ProjectInfo;
import com.example.online_program.entity.Userinfo;
import io.lettuce.core.dynamic.annotation.Param;
import jnr.ffi.annotations.In;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    @Query(value = "update project_info set proj_status=?2 where proj_id=?1 ", nativeQuery = true)
    @Modifying
    public void updateProjectInfo(int proj_id,int proj_status);

    @Query(value = "update project_info set proj_name=?2 where proj_nick_name=?1 ", nativeQuery = true)
    @Modifying
    public void updateProjectName(String proj_nick_name,String name);

    Optional<ProjectInfo> findByProjId(int id);

//    @Query("select h.name as name, avg(r.rating) as averageRating  "from Hotel h left outer join h.reviews r  group by h")
//    public void getUserList

//    @Insert(value = "insert into project_info (proj_description, proj_from_id, proj_name, proj_path, proj_status, proj_type, user_id, is_active, proj_nick_name,proj_create_time,proj_nick_name) values" +
//            "(?2,?3,?4,?5,?7,?8,?9,?10,?1,?6)")

    @Query(value = "insert into project_info (proj_description, proj_from_id, proj_name, proj_path, proj_status, proj_type, user_id, is_active, proj_nick_name,proj_create_time) values" +
            "(?2,?3,?4,?5,?7,?8,?9,?10,?6,?1)", nativeQuery = true)
    @Modifying
    public void insertProj(String create_time_1,String proj_description_2,int proj_from_id_3,String proj_name_4,String proj_path_5,String proj_nick_name_6,int proj_status_7,int proj_type_8,int user_id_9,boolean is_active_10);


    @Query(value = "select userinfo.user_name from project_info join userinfo on userinfo.id=project_info.user_id where proj_nick_name=?1 ", nativeQuery = true)
    @Modifying
    public Set<String> getProjMembers(String proj_nick_name );


}

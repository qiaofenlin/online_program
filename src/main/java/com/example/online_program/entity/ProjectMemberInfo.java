package com.example.online_program.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 项目参与者列表
 */
@Entity
public class ProjectMemberInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_id;

    @Column(nullable = false)
    private Integer proj_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String member_create_time;

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getProj_id() {
        return proj_id;
    }

    public void setProj_id(Integer proj_id) {
        this.proj_id = proj_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMember_create_time() {
        return member_create_time;
    }

    public void setMember_create_time(String member_create_time) {
        this.member_create_time = member_create_time;
    }
}

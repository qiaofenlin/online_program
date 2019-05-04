package com.example.online_program.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: ProjectInfo.java
 * @package_name: online_program
 * @Description: 用户项目表
 */

@Entity
public class ProjectInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proj_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private String proj_path;

    @Column(nullable = false)
    private String proj_name;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp proj_create_time;

    @Column(nullable = false)
    private String proj_description;

    @Column(nullable = false)
    private int proj_type; //star fork own

    @Column
    private int proj_from_id; //项目的来源

    @Column
    private int proj_status; //项目的状态 public private friend

    public int getProj_from_id() {
        return proj_from_id;
    }

    public void setProj_from_id(int proj_from_id) {
        this.proj_from_id = proj_from_id;
    }

    public int getProj_status() {
        return proj_status;
    }

    public void setProj_status(int proj_status) {
        this.proj_status = proj_status;
    }

    public Integer getProj_id() {
        return proj_id;
    }

    public void setProj_id(Integer proj_id) {
        this.proj_id = proj_id;
    }

    public String getProj_path() {
        return proj_path;
    }

    public void setProj_path(String proj_path) {
        this.proj_path = proj_path;
    }

    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public Timestamp getProj_create_time() {
        return proj_create_time;
    }

    public void setProj_create_time(Timestamp proj_create_time) {
        this.proj_create_time = proj_create_time;
    }

    public String getProj_description() {
        return proj_description;
    }

    public void setProj_description(String proj_description) {
        this.proj_description = proj_description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public int getProj_type() {
        return proj_type;
    }

    public void setProj_type(int proj_type) {
        this.proj_type = proj_type;
    }

}

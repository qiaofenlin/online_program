package com.example.online_program.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
    private String proj_create_time;

    @Column
    private String proj_description;

    @Column(nullable = false)
    private int proj_member; //项目成员列表 id

    @Column(nullable = false)
    private int proj_type; //star 0 fork 1 own 2

    @Column
    private int proj_from_id; //项目的来源

    @Column
    private int proj_status; //项目的状态 public 0/ private 1/ friend 2

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

    public String getProj_create_time() {
        return proj_create_time;
    }

    public void setProj_create_time(String proj_create_time) {
        this.proj_create_time = proj_create_time;
    }

    public String getProj_description() {
        return proj_description;
    }

    public void setProj_description(String proj_description) {
        this.proj_description = proj_description;
    }

    public int getProj_member() {
        return proj_member;
    }

    public void setProj_member(int proj_member) {
        this.proj_member = proj_member;
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
    @Override
    public String toString() {
        return "ProjectInfo{" +
                "proj_id:" + proj_id +
                ", user_id:" + user_id +
                ", proj_path:'" + proj_path + '\'' +
                ", proj_name:'" + proj_name + '\'' +
                ", proj_create_time:" + proj_create_time +
                ", proj_description:'" + proj_description + '\'' +
                ", proj_member:'" + proj_member + '\'' +
                '}';
    }
}

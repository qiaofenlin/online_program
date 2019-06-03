package com.example.online_program.entity;

import javax.persistence.*;
import java.io.Serializable;

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
    private Integer projId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String projPath;

    @Column(nullable = false)
    private String projName;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String projCreateTime;

    @Column
    private String projDescription;

//    @Column(nullable = false)
//    private int proj_member; //项目成员列表 id

    @Column(nullable = false)
    private int projType; //star 0 fork 1 own 2

    @Column
    private int projFromId; //项目的来源

    @Column
    private int projStatus; //项目的状态 public 0/ private 1/ friend 2

    @Column
    private Boolean is_active;

    @Column
    private String  proj_nick_name;

    public String getProj_nick_name() {
        return proj_nick_name;
    }

    public void setProj_nick_name(String proj_nick_name) {
        this.proj_nick_name = proj_nick_name;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProjPath() {
        return projPath;
    }

    public void setProjPath(String projPath) {
        this.projPath = projPath;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjCreateTime() {
        return projCreateTime;
    }

    public void setProjCreateTime(String projCreateTime) {
        this.projCreateTime = projCreateTime;
    }

    public String getProjDescription() {
        return projDescription;
    }

    public void setProjDescription(String projDescription) {
        this.projDescription = projDescription;
    }

    public int getProjType() {
        return projType;
    }

    public void setProjType(int projType) {
        this.projType = projType;
    }

    public int getProjFromId() {
        return projFromId;
    }

    public void setProjFromId(int projFromId) {
        this.projFromId = projFromId;
    }

    public int getProjStatus() {
        return projStatus;
    }

    public void setProjStatus(int projStatus) {
        this.projStatus = projStatus;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projId=" + projId +
                ", userId=" + userId +
                ", projPath='" + projPath + '\'' +
                ", projName='" + projName + '\'' +
                ", projCreateTime='" + projCreateTime + '\'' +
                ", projDescription='" + projDescription + '\'' +
                ", projType=" + projType +
                ", projFromId=" + projFromId +
                ", projStatus=" + projStatus +
                '}';
    }
}

package com.example.online_program.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: TerminalInfo.java
 * @package_name: online_program
 * @Description:
 */

@Entity
public class TerminalInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp proj_create_time;

    @Column(nullable = false)
    private String proj_description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "TerminalInfo{" +
                "id:" + id +
                ", user_id:" + user_id +
                ", proj_create_time:" + proj_create_time +
                ", proj_description:'" + proj_description + '\'' +
                '}';
    }
}

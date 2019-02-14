package com.example.online_program.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Created by qfl
 * @Date: 19-2-12
 * @package_name: com.example.online_program.entity
 * @Description:
 */

@Entity
public class UserEnvInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer env_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private String env_name;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp env_create_time;

    @Column(nullable = false)
    private String env_description;

    public Integer getEnv_id() {
        return env_id;
    }

    public void setEnv_id(Integer env_id) {
        this.env_id = env_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getEnv_name() {
        return env_name;
    }

    public void setEnv_name(String env_name) {
        this.env_name = env_name;
    }

    public Timestamp getEnv_create_time() {
        return env_create_time;
    }

    public void setEnv_create_time(Timestamp env_create_time) {
        this.env_create_time = env_create_time;
    }

    public String getEnv_description() {
        return env_description;
    }

    public void setEnv_description(String env_description) {
        this.env_description = env_description;
    }

    @Override
    public String toString() {
        return "{" +
                "env_id :" + env_id +
                ", user_id :" + user_id +
                ", env_name :'" + env_name + '\'' +
                ", env_create_time :" + env_create_time +
                ", env_description :'" + env_description + '\'' +
                '}';
    }
}

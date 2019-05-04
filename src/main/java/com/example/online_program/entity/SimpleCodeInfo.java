package com.example.online_program.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户简易执行工具列表
 */
@Entity
public class SimpleCodeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp code_time;

    public Integer getCode_id() {
        return code_id;
    }

    public void setCode_id(Integer code_id) {
        this.code_id = code_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCode_time() {
        return code_time;
    }

    public void setCode_time(Timestamp code_time) {
        this.code_time = code_time;
    }
}

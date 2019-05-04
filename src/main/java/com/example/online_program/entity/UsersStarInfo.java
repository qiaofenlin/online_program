package com.example.online_program.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class UsersStarInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer star_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private Integer star_user_id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp star_create_time;

    public Integer getStar_id() {
        return star_id;
    }

    public void setStar_id(Integer star_id) {
        this.star_id = star_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStar_user_id() {
        return star_user_id;
    }

    public void setStar_user_id(Integer star_user_id) {
        this.star_user_id = star_user_id;
    }

    public Timestamp getStar_create_time() {
        return star_create_time;
    }

    public void setStar_create_time(Timestamp star_create_time) {
        this.star_create_time = star_create_time;
    }
}

package com.example.online_program.entity;

/**
 * @Created by  qiao
 * @date 18-12-9 下午1:46
 */
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;


@Entity // tell jpa is entity
//@Table(name="tble")  //可以省略
public class Userinfo {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private Date regTime;

    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

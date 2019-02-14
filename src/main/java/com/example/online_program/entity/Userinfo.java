package com.example.online_program.entity;

/**
 * @Created by  qiao
 * @date 18-12-9 下午1:46
 */

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity // tell jpa is entity
//@Table(name="tble")  //可以省略
public class Userinfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp regTime = new Timestamp(System.currentTimeMillis());//默认为当前时间

    @Column(nullable = false)
    private Timestamp birthday;// =new Timestamp(System.currentTimeMillis());;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String email;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
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

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "{" +
                "id :" + id +
                ", pwd :'" + pwd + '\'' +
                ", userName :'" + userName + '\'' +
                ", age :" + age +
                ", regTime :" + regTime +
                ", birthday :" + birthday +
                ", description :'" + description + '\'' +
                ", tel :'" + tel + '\'' +
                ", email :'" + email + '\'' +
                '}';
    }
}

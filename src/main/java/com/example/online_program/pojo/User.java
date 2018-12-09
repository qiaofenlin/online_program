package com.example.online_program.pojo;

import java.util.Date;

/**
 * @Created by  qiao
 * @date 18-10-20 下午3:53
 */

public class User {

    private String name;
    private Integer age;
    private Date birthday;

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.ofofs.eunit.model;

import com.ofofs.eunit.annotation.Rule;

import java.util.Date;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class User extends Person {

    @Rule(minLength = 5, maxLength = 20)
    private String username;

    @Rule(minLength = 8, maxLength = 30)
    private String password;

    private Date birthday;

    private Byte sex;

    private Integer level;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
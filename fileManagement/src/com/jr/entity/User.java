package com.jr.entity;

import java.io.Serializable;

/**
 * @author JiangZhiHwan
 *
 * implements Serializable
 * User对应数据库中db_user表
 * User的属性和数据库表中数据一一对应
 * */
public class User implements Serializable {
/**
 * 属性
 * 构造方法(有参、无参)
 * Getter&&Setter
 * 重写toString()
 * */
    private int id;
    private String userName;
    private String passWord;

    public User() {
    }

    public User(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}

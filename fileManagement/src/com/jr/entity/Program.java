package com.jr.entity;

import java.io.Serializable;

/**
 * @author KangJiHwan
 *
 * program 对应数据库中db_program表里的数据
 * implements Serializable
 * */
public class Program implements Serializable {

    private int proid;
    private String proname;
    private String propath;

    public Program() {
    }

    public Program(int proid, String proname, String propath) {
        this.proid = proid;
        this.proname = proname;
        this.propath = propath;
    }

    public Program(String proname, String propath) {
        this.proname = proname;
        this.propath = propath;
    }

    public Program(String proname) {
        this.proname = proname;
    }

    public Program(int proid, String proname) {
        this.proid = proid;
        this.proname = proname;
    }

    public Program(int programName1) {
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getPropath() {
        return propath;
    }

    public void setPropath(String propath) {
        this.propath = propath;
    }

    @Override
    public String toString() {
        return "Program{" +
                "proid=" + proid +
                ", proname='" + proname + '\'' +
                ", propath='" + propath + '\'' +
                '}';
    }
}

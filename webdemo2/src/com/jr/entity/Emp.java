package com.jr.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类  一一对应表db_emp
 */
public class Emp implements Serializable {

    private Integer empno;
    private String ename;
    private Double sal;
    private Double comm;
    private String job;
    private Date hiredate;
    private Integer mgr;
    private Integer deptno;

    public Emp() {
    }
    public Emp( String ename, Double sal, Double comm, String job, Date hiredate, Integer mgr,Integer deptno) {

        this.ename = ename;
        this.sal = sal;
        this.comm = comm;
        this.job = job;
        this.hiredate = hiredate;
        this.mgr = mgr;
        this.deptno=deptno;
    }

    public Emp(Integer empno, String ename, Double sal, Double comm, String job, Date hiredate, Integer mgr,Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.sal = sal;
        this.comm = comm;
        this.job = job;
        this.hiredate = hiredate;
        this.mgr = mgr;
        this.deptno=deptno;
    }
    public Emp(Integer empno, String ename, Double sal,  String job) {
        this.empno = empno;
        this.ename = ename;
        this.sal = sal;
        this.job = job;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename=" + ename +
                ", sal=" + sal +
                ", comm=" + comm +
                ", job=" + job +
                ", hiredate=" + hiredate +
                ", mgr=" + mgr +
                '}';
    }
}

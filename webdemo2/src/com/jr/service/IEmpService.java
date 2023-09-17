package com.jr.service;

import com.jr.entity.Emp;
import com.jr.pojo.EmpInfo;


import java.util.List;

/**
 * 业务层接口
 */
public interface IEmpService {

    Emp findByEmpinfo(Emp emp);// Emp===8个属性

    List<Emp> findAll(int deptno); //登录成功后，数据展示

    List<Emp> findlikeEname(EmpInfo emp);//模糊查询

    boolean removeByEmpno(int empno); //删除

    //修改=查询id+修改
    Emp findEmpByEmpno(int empno);
    boolean updateEmpByEmpno(Emp emp);

    boolean addEmp(Emp emp);

    List<Emp> getjobs();

}

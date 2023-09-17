package com.jr.dao;

import com.jr.entity.Emp;
import com.jr.pojo.EmpInfo;

import java.util.List;

/**
 * db_emp表的 dao层接口
 *  规定了db_emp 的数据库操作
 */
public interface IEmpDao {

    Emp selectByEmpinfo(Emp emp);// Emp===8个属性

    List<Emp> selectAll(int deptno); //登录成功后，数据展示

    List<Emp> selectlikeEname(EmpInfo emp);//模糊查询

    int  deleteByEmpno(int empno); //删除

    //修改=查询id+修改
    Emp selectEmpByEmpno(int empno);
    int updateEmpByEmpno(Emp emp);

    int insertEmp(Emp emp);

    List<Emp> getjobs();

}

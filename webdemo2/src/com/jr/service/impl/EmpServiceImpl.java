package com.jr.service.impl;

import com.jr.dao.IEmpDao;
import com.jr.dao.impl.EmpDaoImpl;
import com.jr.entity.Emp;
import com.jr.pojo.EmpInfo;
import com.jr.service.IEmpService;


import java.util.List;

public class EmpServiceImpl implements IEmpService {
    private IEmpDao ied=new EmpDaoImpl();


    @Override
    public Emp findByEmpinfo(Emp emp) {
        return ied.selectByEmpinfo(emp);
    }

    @Override
    public List<Emp> findAll(int deptno) {
        return ied.selectAll(deptno);
    }

    @Override
    public List<Emp> findlikeEname(EmpInfo emp) {
        return ied.selectlikeEname(emp);
    }

    @Override
    public boolean removeByEmpno(int empno) {
        return ied.deleteByEmpno(empno)==1?true:false;
    }

    @Override
    public Emp findEmpByEmpno(int empno) {
        return ied.selectEmpByEmpno(empno);
    }

    @Override
    public boolean updateEmpByEmpno(Emp emp) {
        return ied.updateEmpByEmpno(emp)==1?true:false;
    }

    @Override
    public boolean addEmp(Emp emp) {
        return ied.insertEmp(emp)==1?true:false;
    }

    @Override
    public List<Emp> getjobs() {
        return ied.getjobs();
    }
}

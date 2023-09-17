package com.jr.dao.impl;

import com.jr.dao.IEmpDao;
import com.jr.entity.Emp;
import com.jr.pojo.EmpInfo;
import com.jr.util.BaseDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class EmpDaoImpl implements IEmpDao {

    private BaseDao baseDao=new BaseDao();
    /**
     * selectByEmpinfo 方法名
     * @param emp  参数是存储了 登录用的员工编号和员工姓名两个属性的员工对象。
     * @return Emp 返回登录成功后的员工对象信息
     */
    @Override
    public Emp selectByEmpinfo(Emp emp) {
        String sql="select * from emp where empno=? and  ename=?";
        Object[] objs={emp.getEmpno(),emp.getEname()};
        List<Emp> list=baseDao.queryInfo(Emp.class,sql,objs);
        return  list.size()==0?null:list.get(0);
    }

    @Override
        public List<Emp> selectAll(int deptno) {
            String sql="select * from emp where deptno=?";
            return baseDao.queryInfo(Emp.class,sql,deptno);
    }

    @Override
    public List<Emp> selectlikeEname(EmpInfo emp) {
        //不确定：个姓名模糊查询；岗位查询，工资范围查询（小范围）
        StringBuilder strb=new StringBuilder("select * from emp where 1=1");
        List list=new ArrayList();
        if(emp.getEname()!=null){
            strb.append(" and ename like ?");
            list.add("%"+emp.getEname()+"%");
        }if(emp.getJob()!=null){
            strb.append(" and job=?");
            list.add(emp.getJob());
        }if(emp.getLoSal()!=null){
            strb.append(" and sal>=?");
            list.add(emp.getLoSal());
        }if(emp.getHiSal()!=null){
            strb.append(" and sal<=?");
            list.add(emp.getHiSal());
        }
        return baseDao.queryInfo(Emp.class,strb.toString(),list.toArray());
    }

    @Override
    public int deleteByEmpno(int empno) {
        String sql="delete from emp where empno=?";
        return baseDao.updateInfo(sql,empno);
    }

    @Override
    public Emp selectEmpByEmpno(int empno) {
        String sql="select * from emp where empno=?";
        List<Emp> list=baseDao.queryInfo(Emp.class,sql,empno);
        return list.get(0);
    }

    @Override
    public int updateEmpByEmpno(Emp emp) {
        //String sql="update emp set ename=?,job=?,hiredate=?,comm=?,sal=?,mgr=?,deptno=? where empno=?";
        //Object []objs={emp.getEname(),emp.getJob(),emp.getHiredate(),emp.getComm(),emp.getSal(),emp.getMgr(),emp.getDeptno(),emp.getEmpno()};
        //return baseDao.updateInfo(sql,objs);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="update emp set ename='"+emp.getEname()+"',job='"+emp.getJob()+"',hiredate='"+sdf.format(emp.getHiredate())+"',comm="+emp.getComm()+",sal="+emp.getSal()+",mgr="+emp.getMgr()+",deptno="+emp.getDeptno()+" where empno="+emp.getEmpno();
        System.out.println(sql+"....daoimpl");
        return baseDao.updateInfo(sql);
    }

    @Override
    public int insertEmp(Emp emp) {
        String sql="insert into emp values(?,?,?,?,?,?,?,?)";
        Object []objs={null,emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno()};
        return baseDao.updateInfo(sql,objs);
    }

    @Override
    public List<Emp> getjobs() {
        String sql="select distinct job from emp";
        return baseDao.queryInfo(Emp.class,sql);
    }
}













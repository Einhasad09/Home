package com.jr.controller;

import com.google.gson.Gson;
import com.jr.entity.Emp;
import com.jr.pojo.EmpInfo;
import com.jr.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/as")
public class AjaxServlet extends HttpServlet {

    private EmpServiceImpl esi=new EmpServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");// 异步application/json 同步：text/html
        String str=request.getParameter("id");
        if(str.equals("1")){
            login(request,response);
        }else if(str.equals("2")){
            List<Emp> list=esi.getjobs();
            Gson gson=new Gson();
            response.getWriter().println(gson.toJson(list));
        }else if(str.equals("3")){
            findall(request,response);
        }else if(str.equals("4")){
            queryByEmp(request,response);
        }else if(str.equals("5")){
            delByEmpno(request,response);
        }else if(str.equals("6")){
            update(request,response);
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        int empno=Integer.parseInt(request.getParameter("empno"));
        String ename=request.getParameter("ename");
        int mgr=Integer.parseInt(!request.getParameter("mgr").equals("null")?request.getParameter("mgr"):"0");
        String job=request.getParameter("job");
        double sal=Double.parseDouble(request.getParameter("sal"));
        double comm=Double.parseDouble(!request.getParameter("comm").equals("null")?request.getParameter("comm"):"0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate=new Date();
        try {
            hiredate=sdf.parse(request.getParameter("hiredate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int deptno=Integer.parseInt(request.getParameter("deptno"));
        Emp emp=new Emp(empno, ename, sal, comm, job, hiredate, mgr, deptno);

        if(esi.updateEmpByEmpno(emp)){
            response.getWriter().println(true);
        }else{
            response.getWriter().println(false);
        }

    }



    public void delByEmpno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empno=Integer.parseInt(request.getParameter("empno"));
          String s;
          if(esi.removeByEmpno(empno)){
              s="\"info\":true,\"emps\":";
          }else{
              s="\"info\":false,\"emps\":"; //  {"info":false,"emps":[{},{},{}]}
          }
        Gson gson=new Gson();
        List<Emp> list=esi.findlikeEname(new EmpInfo());
        String str=gson.toJson(list);
        System.out.println("{"+s+str+"}");
        response.getWriter().println("{"+s+str+"}");
    }


    public void queryByEmp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        EmpInfo empInfo=new EmpInfo();
        String ename=request.getParameter("ename");
        String job=request.getParameter("job");
        String losal=request.getParameter("losal");
        String hisal=request.getParameter("hisal");
        if(!ename.equals("")){
            empInfo.setEname(ename);
        }if(!job.equals("0")){
            empInfo.setJob(job);
        }if(!losal.equals("")){
            empInfo.setLoSal(Double.parseDouble(losal));
        }if(!hisal.equals("")){
            empInfo.setHiSal(Double.parseDouble(hisal));
        }
        List<Emp> list=esi.findlikeEname(empInfo);
        Gson gson=new Gson();
        response.getWriter().println(gson.toJson(list));

    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //1.设置请求参数字符编码，防止请求参数中文乱码
        request.setCharacterEncoding("utf-8");
        //2.接收参数：
        int empno=Integer.parseInt(request.getParameter("empno"));
        String ename=request.getParameter("ename");
        //3.调用业务层方法：
        Emp emp=new Emp();
        emp.setEmpno(empno);
        emp.setEname(ename);
        Emp e=esi.findByEmpinfo(emp);
        if(e==null){
            response.getWriter().println(false);
        }else{
            request.getSession().setAttribute("emp",e);//标志真正登录成功
            response.getWriter().println(true);
        }
    }

    public void findall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Emp e= (Emp)request.getSession().getAttribute("emp");
        List<Emp> list=esi.findAll(e.getDeptno());
        Gson gson=new Gson();
        response.getWriter().println(gson.toJson(list));
    }

}

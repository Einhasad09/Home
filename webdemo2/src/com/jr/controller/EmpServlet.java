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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/es/*")
public class EmpServlet extends HttpServlet {

    private EmpServiceImpl esi=new EmpServiceImpl();


    //根据请求路径，分发到对应的方法里去
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得uri地址：
        String address=request.getRequestURI();
        //2.截取字符串，获得方法名
        String methodName=address.substring(address.lastIndexOf("/")+1);
        //3.根据方法名，借用反射，执行该方法：
        try {
            Class cla=EmpServlet.class;
            Method method=cla.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            Object obj=cla.getConstructor().newInstance();
            method.invoke(obj,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
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
            System.out.println("登录失败");
        }else{
            request.getSession().setAttribute("emp",e);//标志真正登录成功！
            findall(request,response);
        }
    }

    public void findall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Emp e= (Emp)request.getSession().getAttribute("emp");
        List<Emp> list=esi.findAll(e.getDeptno());
        List<Emp> emps=esi.getjobs();
        request.setAttribute("list",list);
        request.setAttribute("emps",emps);
        request.getRequestDispatcher("/show.jsp").forward(request,response);
    }

    //response.sendRedirect(request.getContextPath()+"/show.jsp");
    /**
     * [jsp里解决办法：<base>]
     * 相对路径写法简单。但是更换地址找不到
     * 绝对路径写起来太麻烦，
     * -----好的办法：jsp: ----<base>
     * [Servlet里表示路径的 不是包名，是url名 ]
     *    绝对路径：转发   / 就表示绝对路径了
     *             重定向   /
     */


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
        List<Emp> emps=esi.getjobs();
        request.setAttribute("list",list);
        request.setAttribute("emps",emps);
        request.getRequestDispatcher("/show.jsp").forward(request,response);
    }

    public void delByEmpno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           int empno=Integer.parseInt(request.getParameter("empno"));
          if(esi.removeByEmpno(empno)){
              findall(request,response);
          }else{
              System.out.println("删除失败");
          }
    }

    public void findByEmpno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empno=Integer.parseInt(request.getParameter("empno"));
        Emp emp=esi.findEmpByEmpno(empno);
        request.setAttribute("emp",emp);
        request.getRequestDispatcher("/ajax/update.jsp").forward(request,response);
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
           findall(request,response);
       }else{
           System.out.println("修改失败！");
       }

    }

/*    public void  getjobs(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Emp> list= esi.getjobs();
        Gson gson=new Gson();
        response.getWriter().println(gson.toJson(list));
        //gson.toJson(list) 的返回值list集合对应的Json格式字符串 [{"":"","":"","job":"XXX","":null},{},{},{},{}]

    }*/
}

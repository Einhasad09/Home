package com.jr.controller;

import com.jr.entity.Emp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ds")
public class DemoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setAttribute("reqStr","req字符串");
                Emp emp=new Emp();
                emp.setEmpno(1234);
                emp.setEname("李四");
                emp.setJob("开发");
                emp.setSal(7000.0);
            request.setAttribute("reqObj",emp);
                List<Emp> list=new ArrayList<>();
                list.add(new Emp(3456,"菲菲",5678.0,"前端1"));
                list.add(new Emp(3457,"小明",5679.0,"前端2"));
                list.add(new Emp(3458,"娜娜",5670.0,"前端3"));
                list.add(new Emp(3459,"小红",5671.0,"前端4"));
            request.setAttribute("reqlist",list);
            request.getRequestDispatcher("el.jsp").forward(request,response);
    }
}

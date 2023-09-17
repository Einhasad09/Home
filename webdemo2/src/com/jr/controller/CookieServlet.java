package com.jr.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cs")
public class CookieServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname=request.getParameter("username");
        if("zhangsan".equals(uname)){
            Cookie coo=new Cookie("uname",uname);
            coo.setMaxAge(60*60*24*7);//设置cookie的有效时间：单位秒
            coo.setPath("/webdemo2/asd");//设置cookie的有效路径
            coo.setPath("/webdemo2/dataShow.jsp");//设置cookie的有效路径

            response.addCookie(coo);
            request.getSession().setAttribute("username",uname);
            //request.getRequestDispatcher("dataShow.jsp").forward(request,response);
            response.sendRedirect("/webdemo2/dataShow.jsp");
        }else{
            System.out.println("登录失败！");
        }
    }
}

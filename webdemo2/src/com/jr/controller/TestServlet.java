package com.jr.controller;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/tts/*")
public class TestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   /*     resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Cookie cookie=new Cookie("username","zs123");
        resp.addCookie(cookie);
        resp.getWriter().println("获得cookie");

*/

        System.out.println("异步请求过来了。。。。");
        System.out.println(request.getParameter("id"));
        int i=Integer.parseInt(request.getParameter("id"));
        if(i==1){
          /*  getjobs(request,response);*/
            System.out.println(request.getRequestURI());
            //1.获得uri地址：
            String address=request.getRequestURI();
            //2.截取字符串，获得方法名
            String methodName=address.substring(address.lastIndexOf("/")+1);
            System.out.println(methodName);
            //3.根据方法名，借用反射，执行该方法：
            try {
                Method method=this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                method.invoke(this,request,response);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    public void getjobs(HttpServletRequest request, HttpServletResponse response){

        System.out.println(".....进来啦@@@");
        System.out.println("i="+request.getParameter("id"));
    }
}

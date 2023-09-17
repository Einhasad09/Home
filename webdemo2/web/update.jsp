<%@ page import="com.jr.entity.Emp" %><%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/15
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
    <base href="<%=basePath%>">
</head>
<body>
    <% Emp emp=(Emp) request.getAttribute("emp");%>
    <form action="es/update" method="post">
        员工编号：<input type="text" name="empno" value="<%=emp.getEmpno()%>"/><br/>
        员工姓名：<input type="text" name="ename" value="<%=emp.getEname()%>"/><br/>
        岗位名称：<input type="text" name="job" value="<%=emp.getJob()%>"/><br/>
        经理编号：<input type="text" name="mgr" value="<%=emp.getMgr()%>"/><br/>
        工资：<input type="text" name="sal" value="<%=emp.getSal()%>"/><br/>
        奖金：<input type="text" name="comm" value="<%=emp.getComm()%>"/><br/>
        入职时间：<input type="text" name="hiredate" value="<%=emp.getHiredate()%>"/><br/>
        部门编号：<input type="text" name="deptno" value="<%=emp.getDeptno()%>"/><br/>
        <input type="submit" value="修改">
    </form>
</body>
</html>

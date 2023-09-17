<%@ page import="com.jr.entity.Emp" %><%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/15
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //修改按钮点击后，发送异步请求：
            $("[type=button]").click(function () {
                var empno=$("[name=empno]").val();
                var ename=$("[name=ename]").val();
                var job=$("[name=job]").val();
                var mgr=$("[name=mgr]").val();
                var sal=$("[name=sal]").val();
                var comm=$("[name=comm]").val();
                var hiredate=$("[name=hiredate]").val();
                var deptno=$("[name=deptno]").val();
                var s="id=6&empno="+empno+"&ename="+ename+"&job="+job+"&mgr="+mgr+"&sal="+sal+"&comm="+comm+"&hiredate="+hiredate+"&deptno="+deptno;
                $.get("as",s,function (data) {
                    if(data){
                        alert("修改成功！");
                    }else{
                        alert("修改失败!");
                    }
                    window.location.href="http://localhost:8081/webdemo2/ajax/show.jsp";
                });
            });
        });
    </script>
</head>
<body>
        员工编号：<input type="text" name="empno" value="${emp.empno}" readonly/><br/>
        员工姓名：<input type="text" name="ename" value="${emp.ename}"/><br/>
        岗位名称：<input type="text" name="job" value="${emp.job}"/><br/>
        经理编号：<input type="text" name="mgr" value="${emp.mgr}"/><br/>
        工资：<input type="text" name="sal" value="${emp.sal}"/><br/>
        奖金：<input type="text" name="comm" value="${emp.comm}"/><br/>
        入职时间：<input type="text" name="hiredate" value="${emp.hiredate}"/><br/>
        部门编号：<input type="text" name="deptno" value="${emp.deptno}"/><br/>
        <input type="button" value="修改">

</body>
</html>

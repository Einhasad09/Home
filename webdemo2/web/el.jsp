<%@ page import="com.jr.entity.Emp" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/16
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    jsp语法获得存储变量
    <%String str=(String)request.getAttribute("reqStr");%>
    <%=str%><br/>
    <%Emp emp=(Emp)request.getAttribute("reqObj");%>
    emp对象属性信息：<%=emp.getEmpno()%>---<%=emp.getEname()%>---<%=emp.getJob()%><br/>
    <table>
        <tr>
            <td>员工编号</td>
            <td>员工姓名</td>
            <td>岗位名称</td>
            <td>工资</td>
        </tr>
        <%List<Emp> list=(List<Emp>) request.getAttribute("reqlist");%>
        <% for(int i=0;i<list.size();i++){ %>
            <tr>
                <td><%=list.get(i).getEmpno()%></td>
                <td><%=list.get(i).getEname()%></td>
                <td><%=list.get(i).getJob()%></td>
                <td><%=list.get(i).getSal()%></td>
            </tr>
        <% }%>
    </table>
    el表达式：
<%--     ${sessionScope.reqStr}--%>
     ${reqStr}<br/>
     ${reqObj.empno} ----${reqObj.ename}---${reqObj.job}
    <table>
        <tr>
            <td>员工编号</td>
            <td>员工姓名</td>
            <td>岗位名称</td>
            <td>工资</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${reqlist}" var="emp">
            <tr>
                <td>${emp.empno}</td>
                <td>${emp.ename}</td>
                <td>${emp.job}</td>
                <td>${emp.sal}</td>
                <td>
                    <a href="ds/del?empno=${emp.empno}">删除</a>
                    <a href="ds/upd?empno=${emp.empno}">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <%
        double score=Math.random()*100;
        pageContext.setAttribute("score",score);
    %>
    成绩是：${score}
    <c:choose>
        <c:when test="${score>=90}">优秀</c:when>
        <c:when test="${score>=80 and score<90}">良好</c:when>
        <c:when test="${score>=70 and score<80}">中等</c:when>
        <c:when test="${score>=60}">及格</c:when>
        <c:otherwise>不及格</c:otherwise>
    </c:choose>



</body>
</html>

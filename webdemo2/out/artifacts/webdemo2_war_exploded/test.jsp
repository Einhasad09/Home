<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/16
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%--<%@ include file="aaa.jsp"%>--%>
<jsp:include page="aaa.jsp"></jsp:include>
<html>
<head>
    <title>Title</title>
</head>
<body>

       <!--   <h1>123</h1>-->
      <%--  <h1>123</h1>--%>
       <%  // <h1></h1>      %>
        <%!
           public void show(){
               System.out.println("show方法");
           }
           int year=2023;
           %>
         <%  int i=90;%>
         <%=year%> ---<%=i%>

    <h1>
       获得aaa页面里的值是： <%=pageContext.getAttribute("str")%>
    </h1>

</body>
</html>

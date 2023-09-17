<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/16
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
            String uname="";
            Cookie[] coos= request.getCookies();
            for (Cookie c:coos) {
                if(c.getName().equals("uname")){
                    uname=c.getValue();
                    break;
                }
            }
        %>
        <h1>登录成功，欢迎<%=uname%>
        </h1>
</body>
</html>

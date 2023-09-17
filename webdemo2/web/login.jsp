<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/16
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
           Cookie[] coos= request.getCookies();
            for (Cookie c:coos) {
                if(c.getName().equals("uname")){
                    response.sendRedirect("/webdemo2/dataShow.jsp");
                }
            }
        %>
        <form action="cs" method="post">
            用户名：<input type="text" name="username"/><br/>
            <input type="submit" value="提交">
        </form>
</body>
</html>

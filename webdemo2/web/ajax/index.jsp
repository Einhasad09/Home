<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/16
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $("[type=button]").click(function () {
                var empno=$("[name=empno]").val();
                var ename=$("[name=ename]").val();
                $.get("as","id=1&empno="+empno+"&ename="+ename,function (data) {
                    eval("var boo="+data);
                    if(boo){
                        window.location.href="http://localhost:8081/webdemo2/ajax/show.jsp"
                    }else{
                        alert("登录失败");
                    }
                });
            });
        });
    </script>
</head>
<body>
        员工编号：<input type="text" name="empno"/><br/>
        员工姓名：<input type="text" name="ename"/><br/>
        <input type="button" value="登录">
</body>
</html>

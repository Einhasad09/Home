
<%@ page import="java.util.List" %>
<%@ page import="com.jr.entity.Emp" %>
<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2023/9/15
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
        <% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
        <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
    <script type="text/javascript">
        $(document).ready(function () {
            alert("123");
            $.get("tts/getjobs","id=1",function (jobs) {
                alert(jobs);
                for(var i=0;i<jobs.length;i++){
                    $("<option>"+jobs[i]+"</option>").appendTo("[name='job']");
                }
            });
        });


    </script>

</head>
<body>
     <div>
         查询条件：
         <form action="es/queryByEmp" method="post">
             根据员工姓名模糊查询：<input type="text" name="ename"/><br/>
             根据岗位查询： <select name="job">
                            <option value="0">--请选择岗位--</option>
                         <%--    <%List<Emp> emps=(List<Emp>)request.getAttribute("emps"); %>--%>
                        <%--     <% for(int i=0;i<emps.size();i++){%>
                                <option value="<%=emps.get(i).getJob()%>"><%=emps.get(i).getJob()%></option>
                             <%}%>--%>
                          </select><br/>
             根据工资范围：<input type="text" name="losal"/>元----<input type="text" name="hisal"/>元<br/>
             <input type="submit" value="查询">
         </form>
     </div>

    <div>
        <% List<Emp> list=(List<Emp>)request.getAttribute("list");%>
        <table>
            <tr>
                <td>员工编号</td><td>员工姓名</td>
                <td>岗位</td><td>经理编号</td>
                <td>工资</td><td>奖金</td>
                <td>入职时间</td><td>部门编号</td>
                <td>操作</td>
            </tr>
           <% for(Emp e:list){%>
            <tr>
                <td><%=e.getEmpno()%></td>
                <td><%=e.getEname()%></td>
                <td><%=e.getJob()%></td>
                <td><%=e.getMgr()%></td>
                <td><%=e.getSal()%></td>
                <td><%=e.getComm()%></td>
                <td><%=e.getHiredate()%></td>
                <td><%=e.getDeptno()%></td>
                <td><a href="es/delByEmpno?empno=<%=e.getEmpno()%>">删除</a>
                    <a href="es/findByEmpno?empno=<%=e.getEmpno()%>">修改</a></td>
            </tr>
           <%}%>
        </table>
    </div>
</body>

</html>

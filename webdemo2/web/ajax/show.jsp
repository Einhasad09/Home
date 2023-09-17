
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
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

        })
        $(document).ready(function () {
            //1.异步获得下拉列表值
            $.get("as","id=2",function (data) {
                JSON.stringify(data);//data是字符串，转成json对象 JSON.stringify()可以。
                for(var i=0;i<data.length;i++){
                    $("<option value='"+data[i].job+"'> "+data[i].job+"</option>").appendTo("[name=job]");
                }
            });
            //2.异步获得table表格的值
            $.get("as","id=3",function (data) {
                falltable(data);
            });
            //3.查询按钮点击事件，异步通信：
            $("[type=button]").click(function () {
                var ename=$("[name=ename]").val();
                var job=$("[name=job]").val();
                var losal=$("[name=losal]").val();
                var hisal=$("[name=hisal]").val();
                var str="id=4&ename="+ename+"&job="+job+"&losal="+losal+"&hisal="+hisal;
                $.get("as",str,function (data) {
                    falltable(data);
                });
            });
        });

        function falltable(data) {
            JSON.stringify(data);
            $("tbody").empty();
            for (var i=0;i<data.length;i++){
                $("<tr>"
                    +"<td>"+data[i].empno+"</td>"
                    +"<td>"+data[i].ename+"</td>"
                    +"<td>"+data[i].job+"</td>"
                    +"<td>"+data[i].mgr+"</td>"
                    +"<td>"+data[i].sal+"</td>"
                    +"<td>"+data[i].comm+"</td>"
                    +"<td>"+data[i].hiredate+"</td>"
                    +"<td>"+data[i].deptno+"</td>"
                    +"<td><a name='删除' value='"+data[i].empno+"'>删除</a><a href='es/findByEmpno?empno="+data[i].empno+"'>修改</a></td>"
                    +"</tr>").appendTo("tbody");
            }
        }
        $(document).on("click","[name=删除]",function(){
            //$(this) 代指 被单击的 删除按钮
            $.get("as","id=5&empno="+$(this).attr("value"),function (data) {
                JSON.stringify(data);
                if(data.info){
                    alert("删除成功！");
                }else{
                    alert("删除失败！");
                }
                falltable(data.emps);
            });
        });

    </script>

</head>
<body>
     <div>
         查询条件：
             根据员工姓名模糊查询：<input type="text" name="ename"/><br/>
             根据岗位查询： <select name="job">
                            <option value="0">--请选择岗位--</option>
                          </select><br/>
             根据工资范围：<input type="text" name="losal"/>元----<input type="text" name="hisal"/>元<br/>
             <input type="button" value="查询">
     </div>
    <div>
        <table>
            <thead>
                <tr>
                    <td>员工编号</td><td>员工姓名</td>
                    <td>岗位</td><td>经理编号</td>
                    <td>工资</td><td>奖金</td>
                    <td>入职时间</td><td>部门编号</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</body>

</html>

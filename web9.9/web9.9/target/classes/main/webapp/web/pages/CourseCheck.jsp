<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2021/11/5
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="main.loginweb.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.loginweb.service.impl.UserServiceImpl" %>
<%@ page import="main.loginweb.bean.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>已选课程</title>
    <base href="http://localhost:8080/web9_9_war_exploded/">
</head>
<body>
<div id="container" style="width: 1800px">
    <div id="header" style="background-color:#FFA500">
        <h1>学生选课管理系统</h1>
        <span>欢迎${sessionScope.user}</span>光临
        <a href="userServlet?action=logout">注销</a>
        <br> <a href="web/pages/MainPage.html">返回主页</a>
    </div>
</div>
<hr />

<h2>已选课程</h2>


<table border="1">
    <tr>
        <th>学生id</th>
        <th>姓名</th>
        <th>性别</th>
        <th>班级</th>
        <th>选课</th>
        <th>分数</th>
    </tr>
    <%
        List<Course> list = new ArrayList<Course>();

    %>
    <tr>
        <td></td>
        <td></td>
        <td><%%></td>
        <td><%%></td>
        <td><%%></td>
        <td><%%></td>

    </tr>
</table>


</body>
</html>


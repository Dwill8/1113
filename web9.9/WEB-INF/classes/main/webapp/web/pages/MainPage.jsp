<%@ page import="main.loginweb.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.loginweb.service.impl.UserServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2021/9/18
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页面</title>
    <base href="http://localhost:8080/web9_9_war_exploded/">
</head>
<body>
<div id="container" style="width: 1800px">
    <div id="header" style="background-color:#FFA500">
        <h1>学生选课管理系统</h1>
        <span>欢迎${sessionScope.user}</span>光临
        <a href="userServlet?action=logout">注销</a>
    </div>
</div>
<hr />

<h2>这里是主页</h2>
<br> <a href="web/pages/Student.jsp">我是学生</a>
<br> <a href="web/pages/Admin.jsp">我是管理员</a>


</body>
</html>

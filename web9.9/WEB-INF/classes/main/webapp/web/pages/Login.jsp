<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2021/10/14
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <base href="http://localhost:8080/web9_9_war_exploded/">
</head>
<body>
<div id="container" style="width: 1800px">
    <div id="header" style="background-color:#FFA500">
        <h1>学生选课管理系统</h1>
    </div>
</div>
<hr />

<form action="LoginServlet" method="post">
    <div class="form-group">
        <h2>登录</h2>
        <input type="text" name="username" class="form-control" placeholder="账号"><br>
        <input type="password" name="password" class="form-control" placeholder="密码">
    </div>
    <button type="submit" value="提交" class="btn btn-default">提交</button>
</form>
<br> <a href="web/pages/Regist.html">没有账号？注册一个</a>
</body>
</html>

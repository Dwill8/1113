<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2021/12/11
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>视频上传页面</title>
    <base href="http://localhost:8080/bili0_war_exploded/">
</head>
<body>
    <div id="container" style="width: 1800px">
    <div id="header" style="background-color:#FFA500">
        <h1>视频站</h1>
    </div>
</div>
    <hr />
    <form action="${pageContext.request.contextPath}/uploadServlet" enctype="multipart/form-data" method="post">
        上传用户：<input type="text" name="username"><br/>
        视频标题：<input type="text" name="videoTitle"><br/>
        上传文件1：<input type="file" name="file1"><br/>
        上传文件2：<input type="file" name="file2"><br/>
        <input type="submit" value="提交">
     </form>
</body>
</html>

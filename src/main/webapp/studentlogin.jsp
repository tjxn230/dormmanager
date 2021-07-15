<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/6
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="login-page">
    <div class="form">

        <form class="login-form" action="stulogin" method="post">
            <h1>学生登录</h1>
            <p>${msg}</p>
            <input  type="text"  placeholder="学生电话" name="phone" value="">
            <input type="password" placeholder="密码" name="password" value="" >
            <input type="submit" value="学生登录"/>

        </form>
    </div>
</div>
<script src="js/login.min.js"></script>
<script src="js/login1.js"></script>

</body>

</html>

 
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
         
        <form class="login-form" action="login" method="post">
        	<p>${msg}</p>
            <input  type="text"  placeholder="用户名" name="a_username" value="">
            <input type="password" placeholder="密码" name="a_password" value="" >
            <input type="submit" value="登陆"/>
            
        </form>
    </div>
</div>
<script src="js/login.min.js"></script>
<script src="js/login1.js"></script>

</body>

</html>
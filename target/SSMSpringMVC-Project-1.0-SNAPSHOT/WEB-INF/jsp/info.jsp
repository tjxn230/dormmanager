<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/8
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息页面</title>
    <script src="../../js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
</head>
<body style="overflow-y: hidden">
<%--弹出充值界面 --%>

<%--主页--%>
<div class="layui-tab-content" >
    <%--   主页面     --%>
    <div class="layui-tab-item layui-show o_div" >
        <span class="o_span" style="height: 50px;padding: 20px;font-weight: bolder;font-family:华文行楷;font-size: 48px">充值提示信息</span>
        <%--                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">--%>
        <div class="layui-col-md6" style="padding: 50px;left: 60px;width:1200px;height:480px;background-color: #F5F5F5;">
            <div class="layui-card" style="width: 1100px">
                <div class="layui-card-header">
                    <h1 align="center">缴费信息</h1>
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    ${msg}
                </div>

            </div>
        </div>
    </div>
</div>

</body>

</html>

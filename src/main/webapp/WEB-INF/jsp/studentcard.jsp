<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/8
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生卡页面</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
</head>
<body style="overflow-y: hidden">
        <div class="layui-tab-content" >
            <div class="layui-tab-item layui-show o_div" >
                <span class="o_span" style="height: 50px;padding: 20px;font-weight: bolder;font-family:华文行楷;font-size: 48px">学生:${studentCard.s_name}，欢迎使用学生服务中心！</span>
                <%--                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">--%>
                <div class="layui-col-md6" style="padding: 50px;left: 60px;width:1200px;height:480px;background-color: #F5F5F5;">
                    <div class="layui-card" style="width: 1100px">
                        <div class="layui-card-header">
                            <h1 align="center">个人信息</h1>
                        </div>
                        <div class="layui-card-body">
                            <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                            学生姓名：${studentCard.s_name}
                        </div>
                        <div class="layui-card-body">
                            <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                            学号：${studentCard.s_studentid}
                        </div>
                        <div class="layui-card-body">
                            <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                            学生性别：${studentCard.s_sex}
                        </div>
                        <div class="layui-card-body">
                            <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                            学生年龄：${studentCard.s_age}
                        </div>
                        <div class="layui-card-body">
                            学生账户余额：${studentCard.s_account}
                        </div>
                        <div class="layui-card-body">
                            学生电话：${studentCard.s_phone}
                        </div>
                        <div class="layui-card-body">
                            学生专业和班级：${studentCard.s_classname}${studentCard.s_classid}班
                        </div>
                        <div class="layui-card-body">
                            学生宿舍号：${studentCard.s_dormbuilding},${studentCard.s_dormitoryid}宿舍
                        </div>
                    </div>
                </div>
            </div>
        </div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<%--<div class="footer">--%>
<%--    <div class="copyright">学生服务中心系统</div>--%>
<%--</div>--%>
<!-- 底部结束 -->
</body>
</html>

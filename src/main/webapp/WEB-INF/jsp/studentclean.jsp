<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/9
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍个人卫生</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
</head>
<body style="overflow-y: hidden">
<div class="layui-tab-content" >
    <div class="layui-tab-item layui-show o_div" >
        <span class="o_span" style="height: 50px;padding: 20px;font-weight: bolder;font-family:华文行楷;font-size: 48px">查看宿舍个人卫生</span>
        <%--                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">--%>
        <div class="layui-col-md6" style="padding: 50px;left: 60px;width:1200px;height:480px;background-color: #F5F5F5;">
            <div class="layui-card" style="width: 1100px">
                <div class="layui-card-header">
                    <h1 align="center">宿舍个人卫生信息</h1>
                </div>
                <table class="layui-table">
                    <tr>
                        <th>姓名</th>
                        <th>个人卫生评分</th>
                    </tr>
                    <c:forEach items="${studentCleans}" var="stuclean">
                        <tr>
                            <td>${stuclean.s_name}</td>
                            <td>${stuclean.s_grade}</td>
                        </tr>
                    </c:forEach>
                </table>
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

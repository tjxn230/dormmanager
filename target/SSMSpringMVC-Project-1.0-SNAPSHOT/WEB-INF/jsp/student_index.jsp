<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/6
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生端服务中心</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
    <style>
        .layui-tab-item{
            height:99%
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学生端服务中心</div>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="menu">
                <li class="layui-nav-item" ><a href="javascript:;" data-url="stucard?id=${sessionScope.sa.s_studentid}"><i class="layui-icon">&#xe65e;</i>学生卡充值中心</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="stucard?id=${sessionScope.sa.s_studentid}"><i class="layui-icon">&#xe60a;</i>查看学生卡</a></dd>
                        <dd><a href="javascript:;"  data-url="selectdebtbyid?id=${sessionScope.sa.s_studentid}"><i class="layui-icon">&#xe659;</i>在线缴费</a></dd>
                    </dl></li>
                <li  class="layui-nav-item"><a href="javascript:;" data-url="choosedorm?id=${sessionScope.sa.s_studentid}"><i class="layui-icon">&#xe770;</i>学生宿舍信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="choosedorm?id=${sessionScope.sa.s_studentid}"><i class="layui-icon">&#xe609;</i>申请宿舍</a></dd>
                        <dd><a href="javascript:;" data-url="selectdorminfo?id=${sessionScope.sc.s_dormitoryid}&building=${sessionScope.sc.s_dormbuilding}"><i class="layui-icon">&#xe63c;</i>宿舍信息</a></dd>
                        <dd><a href="javascript:;" data-url="stugrade?dormitoryid=${sessionScope.sc.s_dormitoryid}"><i class="layui-icon">&#xe66f;</i>宿舍个人卫生情况</a></dd>
                    </dl><li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab layui-tab-card" lay-allowClose="true" style="height:96%;" lay-filter="desktop">
            <ul class="layui-tab-title">
                <li>欢迎</li>
            </ul>
            <div class="layui-tab-content" style="height:99%;">
                <div class="layui-tab-item" style="height:99%;">欢迎学生:${sessionScope.sa.s_name},使用学生服务中心</div>
            </div>
        </div>
    </div>
    <div class="layui-footer">&copy;版权所有：Baby-Bus组</div>
</div>
<script>
    layui.use(["element","layer"],function(){
        var element = layui.element;
        var layer = layui.layer;

         element.on("nav(menu)",function(elem){
             layer.msg(elem.attr("data-url"));
             var dataUrl =  elem.attr("data-url");
              var text = elem.text();

            element.tabAdd("desktop",{
                title:text,
                content:'<iframe data-frameid="if11" scrolling="auto" frameborder="0" src="'+dataUrl+'" style="width:100%;height:99%;"></iframe>',
                id:'m1'
            });
        });

    });
</script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/9
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生端宿舍信息</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
</head>
<body style="overflow-y: hidden">
<div class="layui-tab-content" >
    <div class="layui-tab-item layui-show o_div" >
        <span class="o_span" style="height: 50px;padding: 20px;font-weight: bolder;font-family:华文行楷;font-size: 48px">查看宿舍信息</span>
        <button data-method="notice" class="layui-btn"><i class="layui-icon">&#xe631;</i>宿舍报修</button>
        <%--                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">--%>
        <div class="layui-col-md6" style="padding: 50px;left: 60px;width:1200px;height:480px;background-color: #F5F5F5;">
            <div class="layui-card" style="width: 1100px">
                <div class="layui-card-header">
                    <h1 align="center">宿舍信息</h1>
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    宿舍楼：${dormitoryInfo.d_dormbuilding}
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    宿舍号：${dormitoryInfo.d_id}
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    宿舍卫生评分：${dormitoryInfo.d_grade}分,${msg}
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    寝室床位：${dormitoryInfo.d_bedtotal}人寝
                </div>
                <div class="layui-card-body">
                    可用床位：剩余${dormitoryInfo.d_bed}床位
                </div>
                <div class="layui-card-body">
                    楼管姓名：${dormitoryInfo.a_name}
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

<%--弹出页面1--%>
<div class="layui-row" id="showid" style="visibility: hidden">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>宿舍报修</legend>
    </fieldset>
    <div class=" layui-col-md-offset2 layui-col-md8">
        <form class="layui-form" lay-filter="formTest" action="addrepair">
            <div class="layui-form-item">
                <label class="layui-form-label">宿舍号</label>
                <div class="layui-input-inline">
                    <input type="text" name="d_id" readonly="true" lay-verify="title"  placeholder="${dormitoryInfo.d_id}" class="layui-input" value="${dormitoryInfo.d_id}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">宿舍楼</label>
                <div class="layui-input-inline">
                    <input type="text" name="d_dormbuilding"  readonly="true" lay-verify="title" value="${dormitoryInfo.d_dormbuilding}" placeholder="${dormitoryInfo.d_id}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">损坏物品</label>
                <div class="layui-input-inline">
                    <input type="text" name="reason" lay-verify="title"  placeholder="${请输入损坏的物品}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="addbtn">提交</button>
                    <a href="selectdorminfo?id=${dormitoryInfo.d_id}&building=${dormitoryInfo.d_dormbuilding}" class="layui-btn">取消</a><br>
                </div>
            </div>
        </form>
    </div>
</div>
</body>

<%--弹出页面1js--%>
<script>
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        var active = {
            notice: function () {
                //示范一个公告层
                layer.open({
                    type: 1
                    ,
                    title: false //不显示标题栏
                    ,
                    closeBtn: false
                    ,
                    area: ['500px', '400px']
                    ,
                    offset: 'auto'
                    ,
                    shade: 0.8
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,

                    btnAlign: 'c'
                    ,
                    moveType: 1 //拖拽模式，0或者1
                    ,
                    content: $('#showid')
                    ,
                });
                $("#showid").css("visibility","visible");
            }
        };
        $('.layui-btn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, this) : '';
            //visibility: visible
        });
    });
</script>
</html>

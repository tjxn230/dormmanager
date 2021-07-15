<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/8
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>缴费页面</title>
    <script src="../../js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
</head>
<body style="overflow-y: hidden">
<%--弹出充值界面 --%>

<%--主页--%>
<div class="layui-tab-content" >
    <%--按钮--%>
<%--   主页面     --%>
    <div class="layui-tab-item layui-show o_div" >
        <span class="o_span" style="height: 50px;padding: 20px;font-weight: bolder;font-family:华文行楷;font-size: 48px">${studentAccount.s_name}同学，欢迎使用缴费系统！</span>
        <button data-method="notice" class="layui-btn"><i class="layui-icon">&#xe65e;</i>金额充值</button>
<%--        <button data-method="notice" data-type="getCheckData"  class="layui-btn">编辑课程</button>--%>
        <a href="paymoney?stuid=${studentAccount.s_studentid}&account=${studentAccount.s_account}&debt=${studentAccount.s_debt}" class="layui-btn layui-btn-danger"><i class="layui-icon">&#xe659;</i>缴费</a>
        <%--                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">--%>
        <div class="layui-col-md6" style="padding: 50px;left: 60px;width:1200px;height:480px;background-color: #F5F5F5;">
            <div class="layui-card" style="width: 1100px">
                <div class="layui-card-header">
                    <h1 align="center">缴费信息</h1>
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    本学期共欠费${studentAccount.s_debt}元,账户余额还有${studentAccount.s_account}<br>
                    ${msg}
                </div>
            </div>
        </div>
    </div>
</div>
<%--弹出页面1--%>
<div class="layui-row" id="showid" style="visibility: hidden">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>存入金钱</legend>
    </fieldset>
    <div class=" layui-col-md-offset2 layui-col-md8">
        <form class="layui-form" lay-filter="formTest" action="updateaccount">
            <div class="layui-form-item">
                <label class="layui-form-label">充值金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_account" lay-verify="title"  placeholder="请输入要充值的金钱" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">学生电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_phone" lay-verify="title" autocomplete="off" placeholder="请输入要充值账户的电话" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">支付方式</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_payway" lay-verify="title" autocomplete="off" placeholder="请选择支付宝或者微信" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="addbtn">提交</button>
                    <a href="selectdebtbyid?id=${studentAccount.s_studentid}" class="layui-btn">取消</a><br>
<%--显示二维码的连接--%>
                    <a href="javascript:;" class='show' >显示支付宝充值二维码</a><br>
                    <a href="javascript:;" class='show1' >显示微信充值二维码</a>
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

<%--弹出二维码--%>
<script type="text/javascript">
    // layer.alert('Hello world');
    $(function(){
        $('.show').on('click',function(){
            var img = '<img src="../../images/shoukuanma.jpg">'
            layer.open({
                type: 1,//Page层类型
                // area: ['500px', '300px'],
                area: ['500px','500px'],
                title: '支付宝支付二维码',
                shade: 0.6 ,//遮罩透明度
                maxmin: true ,//允许全屏最小化
                anim: 1 ,//0-6的动画形式，-1不开启
                content: img
            });
        });
    });

        $(function(){
            $('.show1').on('click',function(){
                var img = '<img src="../../images/vxerweima.jpg">'
                layer.open({
                    type: 1,//Page层类型
                    // area: ['500px', '300px'],
                    area: ['500px','500px'],
                    title: '微信支付二维码',
                    shade: 0.6 ,//遮罩透明度
                    maxmin: true ,//允许全屏最小化
                    anim: 1 ,//0-6的动画形式，-1不开启
                    content: img
                });
            });
        });
</script>
</html>

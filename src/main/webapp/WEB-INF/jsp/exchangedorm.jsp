<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/12
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>调换宿舍页面</title>
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
        <span class="o_span" style="height: 50px;padding: 20px;font-weight: bolder;font-family:华文行楷;font-size: 48px">${studentAccount.s_name}同学，欢迎选择宿舍</span>
        <button data-method="notice" class="layui-btn"><i class="layui-icon">&#xe609;</i>选择宿舍</button>
        <%--        <button data-method="notice" data-type="getCheckData"  class="layui-btn">编辑课程</button>--%>
        <%--                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">--%>
        <div class="layui-col-md6" style="padding: 50px;left: 60px;width:1200px;height:480px;background-color: #F5F5F5;">
            <div class="layui-card" style="width: 1100px">
                <div class="layui-card-header">
                    <h1 align="center">宿舍信息</h1>
                </div>
                <div class="layui-card-body">
                    <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                    ${msg}
                </div>
            </div>
        </div>
    </div>
</div>
<%--弹出页面1--%>
<div class="layui-row" id="showid" style="visibility: hidden">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>选择宿舍</legend>
    </fieldset>
    <div class=" layui-col-md-offset2 layui-col-md8">
        <form class="layui-form" lay-filter="formTest" action="exchangedorm">
            <div class="layui-form-item">
                <label class="layui-form-label">学生id</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_studentid" lay-verify="s_studentid" readonly="readonly" value="${studentCard.s_studentid}" placeholder="${studentCard.s_studentid}" class="layui-input">
                </div>
            </div>
<%--      选择宿舍      --%>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">可选择的宿舍</label>
                    <div class="layui-input-inline">
                        <!--                            <input type="text" name="ithings" lay-verify="required" autocomplete="off" class="layui-input">-->
                        <select name="s_dormitoryid" lay-verify="required" lay-search="" id="s_dormitoryid" lay-filter="s_dormitoryid">
                            <option  value=""></option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">宿舍楼号</label>
                    <div class="layui-input-inline">
                        <input type="text" readonly="readonly"  name="d_dormbuilding" lay-verify="required" lay-filter="d_dormbuilding" class="layui-input layui-disabled" id="d_dormbuilding">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="addbtn">提交</button>
                    <a href="choosedorm?id=${studentCard.s_studentid}" class="layui-btn">取消</a><br>
                </div>
            </div>
        </form>
    </div>
</div>


</body>
<%--弹出页面1js--%>
<%--<script>--%>
<%--    layui.use(['layer','form'], function() { //独立版的layer无需执行这一句--%>
<%--        var $ = layui.jquery, layer = layui.layer,form = layui.form ; //独立版的layer无需执行这一句--%>
<%--        var active = {--%>
<%--            notice: function () {--%>
<%--                //示范一个公告层--%>
<%--                layer.open({--%>
<%--                    type: 1--%>
<%--                    ,--%>
<%--                    title: false //不显示标题栏--%>
<%--                    ,--%>
<%--                    closeBtn: false--%>
<%--                    ,--%>
<%--                    area: ['500px', '400px']--%>
<%--                    ,--%>
<%--                    offset: 'auto'--%>
<%--                    ,--%>
<%--                    shade: 0.8--%>
<%--                    ,--%>
<%--                    id: 'LAY_layuipro' //设定一个id，防止重复弹出--%>
<%--                    ,--%>

<%--                    btnAlign: 'c'--%>
<%--                    ,--%>
<%--                    moveType: 1 //拖拽模式，0或者1--%>
<%--                    ,--%>
<%--                    content: $('#showid')--%>
<%--                    ,--%>
<%--                });--%>
<%--                $("#showid").css("visibility","visible");--%>
<%--            }--%>
<%--        };--%>
<%--        $('.layui-btn').on('click', function(){--%>
<%--            var othis = $(this), method = othis.data('method');--%>
<%--            active[method] ? active[method].call(this, this) : '';--%>
<%--            //visibility: visible--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
<script>
    layui.use(['form', 'jquery'], function(){
        var  $ = layui.jquery,
            form = layui.form;

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


                //下拉框查询的ajax必须要在function里
                //先查询有哪些宿舍
                $.ajax({
                    url: '../selectdormid',
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        $.each(data, function (index, item) {
                            $('#s_dormitoryid').append(new Option(item));// 下拉菜单里添加元素
                        });
                        layui.form.render("select");
//重新渲染 固定写法
                    }
                });

                //查询宿舍号对应的宿舍楼
                form.on('select(s_dormitoryid)', function(data){
                    console.log(data.elem); //得到select原始DOM对象
                    console.log(data.value); //得到被选中的值
                    console.log(data.othis); //得到美化后的DOM对象
                    $.post('../selectbydormid?s_dormitoryid='+data.value,function(rs){
                        //item是返回的数据集合中的每个元素
                        $.each(rs.data, function (index, item) {
                            form.val("formTest", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                                "d_dormbuilding": item.d_dormbuilding,
                            });
                            $("#d_dormbuilding").attr("value",item);
                            // $("#iillprice").attr("value",item.illprice);
                            // $("#igrade").attr("value",item.grade);
                        });
                        //重新渲染下拉框
                        form.render('select');
                        form.render();
                    })
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


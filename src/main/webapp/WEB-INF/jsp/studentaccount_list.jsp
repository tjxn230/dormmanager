<%--
  Created by IntelliJ IDEA.
  User: Tony Stark
  Date: 2021/7/13
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="com.icss.domain.StudentAccount" %>


<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<!--修改密码页面-->
<script type="text/html" id="show2">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改密码</legend>
    </fieldset>
    <div class="layui-row">
        <div class="layui-col-md10 layui-col-md-offset1">
            <form class="layui-form" action="password" method="post" lay-filter="formTeste">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学生学号</label>
                        <div class="layui-input-inline">
                            <input type="text"  name="s_studentid" lay-verify="required" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">新密码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_password" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="btn">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</script>

<!--修改欠款页面-->
<script type="text/html" id="show">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改欠款</legend>
    </fieldset>
    <div class="layui-row">
        <div class="layui-col-md10 layui-col-md-offset1">
            <form class="layui-form" action="debt" method="post" lay-filter="formTeste">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学生学号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_studentid" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">欠款</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_debt" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="addbtn">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</script>
<%--标题--%>
<fieldset class="layui-elem-field">
    <legend>学生账户信息页面</legend>
    <div class="layui-field-box">
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath}/golist" title="刷新">
            <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
</fieldset>

<table class="layui-hide" id="LAY_table_user"  lay-filter="user"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn-normal  layui-btn" lay-event="detail">修改密码</a>
    <a class="layui-btn-normal  layui-btn" lay-event="edit">修改欠款</a>
</script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use(['form', 'jquery','table'], function(){

        var table = layui.table;
        var  $ = layui.jquery,
            form = layui.form;
        table.render({
            elem: '#LAY_table_user'
            ,url: '../findStudentAccount'
            ,cols: [[
                {field: 's_studentid', title: '学生学号', width: 170}
                , {field: 's_password', title: '学生密码', width: 160, sort: true,}
                , {field: 's_phone', title: '学生手机号', width: 250}
                , {field: 's_name', title: '学生姓名', width: 180, sort: true,}
                , {field: 's_account', title: '学生账户余额', width: 150}
                , {field: 's_payway', title: '支付方式', width: 150}
                , {field: 's_debt', title: '学生欠款', width: 150}
                , {width: 230, toolbar: "#barDemo", title: '操作'}
            ]]
            ,id: 'testReload'
            ,page: true
            ,page: true
            ,limits: [3,5,10]  //一页选择显示3,5或10条数据
            ,limit: 5 //一页显示10条数据
            ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据，res为从url中get到的数据
                var result;
                console.log(this);
                console.log(JSON.stringify(res));
                if(this.page.curr){
                    result = res.data.slice(this.limit*(this.page.curr-1),this.limit*this.page.curr);
                }
                else{
                    result=res.data.slice(0,this.limit);
                }
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": result //解析数据列表
                };
            }
        });

        //监听工具条
        table.on('tool(user)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    title: '修改密码',
                    type: 1,
                    content: $('#show2').html(),    // 设置跳转的url，跳转到对应的页面
                    area: ['450px','420px'],
                    yes:function(index,layero)
                    {

                        //index为当前层索引
                        //layero 为 弹出层对象
                        //在回调函数末尾添加 “return false”可以禁止点击该按钮关闭弹出层
                        return false;
                    },
                    btn2:function(index,layero){//按钮二回到
                        return false;
                    }
                });


            }  else if(obj.event === 'edit'){
                layer.open({
                    title: '修改欠款',
                    type: 1,
                    content: $('#show').html(),    // 设置跳转的url，跳转到对应的页面
                    area: ['450px','420px'],
                    yes:function(index,layero)
                    {

                        //index为当前层索引
                        //layero 为 弹出层对象
                        //在回调函数末尾添加 “return false”可以禁止点击该按钮关闭弹出层
                        return false;
                    },
                    btn2:function(index,layero){//按钮二回到
                        return false;
                    }
                });


            }
        });
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>
</html>

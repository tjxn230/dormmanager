<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>天津市大学软件学院在线管理系统</title>
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="css/index-css.css" type="text/css" media="all"/>
    <script src="js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="bootstrap-3.4.1/dist/css/bootstrap.css">

    <style>
        .carousel-control.left {
            background-image:none !important;
            background-repeat: repeat-x;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#80000000', endColorstr='#00000000', GradientType=1);
        }
        .carousel-control.right {
            left: auto;
            right: 0;
            background-image:none !important;
            background-repeat: repeat-x;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00000000', endColorstr='#80000000', GradientType=1);
        }

    </style>

</head>

<body>

 <div id="hearder2" class="clearfix">
    <div class="logo">
        <img src="images/logo.jpg">
    </div>
    <div class="logo-name">天津市大学软件学院宿舍管理中心</div>
    <div class="form-tools">
        <form id="search" name="search" method="post">
            <div class="tools-link">
                <a href="studentlogin.jsp" target="_blank">学生登录入口</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="teacherlogin.jsp" target="_blank">老师登录入口</a>
            </div>

        </form>
    </div>
</div>
<div id="menu-box" class="clearfix">
    <ul>
        <li><a href="#">首页</a></li>
        <li><a href="#">中心概况</a></li>
        <li><a href="#">师资队伍</a></li>
        <li><a href="#">设备资源</a></li>
        <li><a href="#">教学模式</a></li>
        <li><a href="#">网上教学</a></li>
        <li><a href="#">新闻中心</a></li>
        <li><a href="#">创新成果</a></li>
        <li><a href="#https://baike.baidu.com/item/%E5%A4%A9%E6%B4%A5%E5%B8%82%E5%A4%A7%E5%AD%A6%E8%BD%AF%E4%BB%B6%E5%AD%A6%E9%99%A2/8774780?fr=aladdin">联系我们</a></li>
    </ul>
</div>
<div id="slider-box" style="background:url(images/slider3.jpg) center center;"></div>
<!-- div1 -->

<div id="div1" class="clearfix">
    <div class="left260">
        <div class="title-box">学生登录</div>
        <form id="form1" name="form1" method="post" action="stulogin">
            <input type="text" name="phone" id="textfield2" placeholder="手机号码" class="uname">
            <input type="password" name="password" id="password" placeholder="密码" class="upass">
            <div class="t-link" style="font-size: xx-small">
                <input type="checkbox" name="checkbox" id="checkbox" class="chek">记住密码
                <a href="#" target="_blank">忘记密码？</a>
            </div>
            <input type="submit" name="submit" id="submit" value="登录" class="input-login">
            <input type="button" name="button" id="button" value="注册" class="input-but" onClick="window.open('register.jsp')">
        </form>
    </div>
    <div class="center450">
        <div class="title-box">中心简介</div>
        <p><img src="images/首页图.jpg" align="left">天津软件与信息技术服务业人才培养基地——天津市大学软件学院，地理位置优越，
            交通便利。紧邻地铁3号线大学城站，地铁可直达天津站与天津南站，换乘地铁2号线可到达天津滨海国际机场，周边建有天津工业大学
            、天津师范大学、天津理工大学...<a href="https://www.tjise.edu.cn/">查看更多...</a></p>
    </div>
    <div class="right260">
        <ul>
            <li style="width: 86px;height: 86px">
                <a href="#" title="中心特色"><img src="images/icon-1.png"></a><br />
                <a href="#" style="color:#d81e06;">中心特色</a>
            </li>
            <li  style="width: 86px;height: 86px">
                <a href="#" title="申请材料"><img src="images/icon-2.png"></a><br />
                <a href="#" style="color:#eda761;">申请材料</a>
            </li>
            <li style="width: 86px;height: 86px">
                <a href="#" title="预习系统"><img src="images/icon-3.png"></a><br />
                <a href="#" style="color:#469892;">预习系统</a>
            </li>
            <li  style="width: 86px;height: 86px">
                <a href="#" title="评分系统"><img src="images/icon-4.png"></a><br />
                <a href="#" style="color:#4094ac;">评分系统</a>
            </li>
            <li style="width: 86px;height: 86px">
                <a href="#" title="预约系统"><img src="images/icon-5.png"></a><br />
                <a href="#" style="color:#1c6dbf;">预约系统</a>
            </li>
            <li style="width: 86px;height: 86px">
                <a href="#" title="考试系统"><img src="images/icon-6.png"></a><br />
                <a href="#" style="color:#86309f;">考试系统</a>
            </li>
            <li style="width: 86px;height: 86px">
                <a href="#" title="虚拟仿真"><img src="images/icon-7.png"></a><br />
                <a href="#" style="color:#d4237a;">虚拟仿真</a>
            </li>
            <li style="width: 86px;height: 86px">
                <a href="#" title="报告系统"><img src="images/icon-8.png"></a><br />
                <a href="#" style="color:#1a7355;">报告系统</a>
            </li>
            <li style="width: 86px;height: 86px">
                <a href="#" title="设备中心"><img src="images/icon-9.png"></a><br />
                <a href="#" style="color:#d4237a;">设备中心</a>
            </li>
        </ul>
    </div>


</div>
 <div></div>
 <div>
<div class="lunbohezi">
    <div class="lunbo"><!--轮播图-->

        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="images/lunbo.png" alt="...">
                </div>
                <div class="item">
                    <img src="images/lunbo1.jpg" alt="...">
                </div>
                <div class="item">
                    <img src="images/lunbo2.jpg" alt="...">
                </div>
                <div class="item">
                    <img src="images/lunbo3.jpg" alt="...">
                </div>
                <div class="item">
                    <img src="images/lunbo4.jpg" alt="...">
                </div>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span> </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span> </a>
        </div>
        </div>
    </div>


</div>

</div>

<div class="banquan">
    <div class="banquan01" style="height: 120px;padding: 15px">
        版权所有 天津市大学软件学院<br>
        地址：天津市西青区宾水西道399号 | 邮编：300387<br>
        津ICP备10200771号 | 津教备0499号<br>
    </div>
    <div class="banquan02">
        <img src="images/erweima.jpg">
    </div>

</div>




<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery-1.11.3.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>
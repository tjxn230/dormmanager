<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021-07-10
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>天津市大学软件学院寝室注册系统</title>
    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="js/jquery-1.11.3.min.js" />

</head>

<body class="login-layout light-login"
      style="background-image: url(images/beijingtu.jpg);
	  background-repeat: no-repeat;
	  background-size:cover;
	  ">

<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1 class="black"align="center">
                            天津市大学软件学院
                        </h1>
                        <h4 class="black" id="id-company-text">寝室管理系统注册</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="signup-box" class="signup-box widget-box visible no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header green lighter bigger">
                                        <i class="ace-icon fa fa-users blue"></i>
                                        用户注册
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>填写信息: </p>

                                    <form id="register" name="register" method="post" action="registerstudent">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<select name="s_classname" id="select" class="form-control">
                                                              <option value="" selected>--- 选择科系名称 ---</option>
                                                              <option value="计算机与科学">计算机与科学</option>
                                                              <option value="软件工程">软件工程</option>
                                                              <option value="人工智能">人工智能</option>
                                                              <option value="数字媒体技术">数字媒体技术</option>
                                                              <option value="大数据">大数据</option>
                                                              <option value="3D建模">3D建模</option>
                                                            </select>
														</span>
                                            </label>

                                            <label class="block clearfix">
                                                    	<span class="block input-icon input-icon-right">
                                                    		<input type="text" class="form-control" name="s_name" placeholder="用户名" />
                                                    		<i class="ace-icon fa fa-user"></i>

                                                    	</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="sex" class="form-control" name="s_sex" placeholder="性别" />
															<i class="ace-icon fa fa-male" ></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="age" class="form-control" name="s_age" placeholder="年龄" />
															<i class="ace-icon fa fa-calendar"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="stuid" class="form-control" name="s_studentid" placeholder="学号" />
															<i class="ace-icon fa fa-info"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="stuclass" class="form-control"  name="s_classid" placeholder="班级" />
															<i class="ace-icon fa fa-users"></i>
														</span>
                                            </label>





                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id ="pwd1" class="form-control" name="s_password" placeholder="密码" />
															<i class="ace-icon fa fa-key"></i>
														</span>
                                            </label>


                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id ="pwd2" class="form-control" onkeyup='validate()' placeholder="确认密码" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>

                                                <span id="tishi"></span></input>
                                            </label>




                                            <script>
                                                function validate() {
                                                    var password = document.getElementById("pwd1").value;
                                                    var repassword = document.getElementById("pwd2").value;

                                                    if(password == repassword) {
                                                        document.getElementById("tishi").innerHTML="<br><font color='green'>两次密码输入一致</font>";
                                                        document.getElementById("submit").disabled = false;

                                                    }else {
                                                        document.getElementById("tishi").innerHTML="<br><font color='red'>两次输入密码不一致!</font>";
                                                        document.getElementById("submit").disabled = true;
                                                    }
                                                }
                                            </script>




                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="stutel" class="form-control" name="s_phone" id="phone" placeholder="电话" />
															<i class="ace-icon fa fa-phone"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="input-icon input-icon-right" style="width:100px;display: inline-block;">
															<input type="text" class="form-control" name="yanzhengma" placeholder="输入验证码" /><br>
                                                            ${msg}
														</span>
                                            </label>

                                            <button  type="button" class="width-50 pull-right btn btn-sm"
                                                    class="bigger-110" onclick="send()">发送短信验证码</button>


                                            <script>
                                              function send(){
                                                    var resultjson;
                                                    var phone = document.getElementById("phone").value;
                                                    $.ajax(
                                                        {
                                                            url: "../send?phone="+phone,
                                                            type: "post",
                                                            async:false,
                                                            dataType:'text',
                                                            success: function (result) {
                                                                resultjson=result;
                                                            },
                                                            error: function (status) {
                                                                console.log("error")
                                                            }
                                                        });
                                                    if (resultjson=='success'){
                                                        alert("短信发送成功");
                                                    }else{
                                                        alert("短信发送失败，请联系管理员处理")
                                                    }
                                                }
                                            </script>





                                            <label class="block">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl">
															接受
															<a href="#">用户协议</a>
														</span>
                                            </label>

                                            <div class="space-24"></div>

                                            <div class="clearfix">
                                                <button type="reset" class="width-30 pull-left btn btn-sm">
                                                    <i class="ace-icon fa fa-refresh"></i>
                                                    <span class="bigger-110">重置</span>
                                                </button>

                                                <button type="submit" class="width-65 pull-right btn btn-sm btn-success">
                                                    <span class="bigger-110" >注册</span>

                                                    <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>

                                <div class="toolbar center">
                                    <a href="studentlogin.jsp" class="back-to-login-link">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        返回登录
                                    </a>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.signup-box -->
                    </div><!-- /.position-relative -->


                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<script src="assets/js/jquery.2.1.1.min.js"></script>

<script src="assets/js/jquery.1.11.1.min.js"></script>



<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>


<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>


</body>
</html>

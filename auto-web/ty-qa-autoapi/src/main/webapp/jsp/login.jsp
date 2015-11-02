<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<link href="${ctx}/resources/css/reportApi.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/head.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
<script src="${ctx}/resources/js/highcharts.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/report.js" type="text/javascript"></script>
<script type="text/javascript">
	function doLogin(){
		if($("#username").val().length==""){
			alert("请输入用户名！");
			return false;
		}
		if($("#password").val().length==""){
			alert("请输入密码！");
			return false;
		}
		$.post("${ctx}/reportLogin/login",$("#loginForm").serialize(),
				function(data){
			if(data && $.trim(data)=="success"){
				window.location.href ="${ctx}/list";
			}else{
				alert("登陆失败!");
			}
		});
	}
	
</script>
</head>
<body>

<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1><small>登录</small></h1>
			</div>
			<div class="login-content ">
			<div class="form">
			<form action="" method="post" id="loginForm" style="text-align: center;">
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
							<input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							<input type="text" id="password" name="password" class="form-control" placeholder="请输入用密码">
						</div>
					</div>
				</div>
				<div class="form-group form-actions">
					<div class="col-xs-4 col-xs-offset-4 " >
						<button type="button" onclick="doLogin()" class="btn btn-sm btn-info"><span  class="glyphicon glyphicon-off"></span> Login</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
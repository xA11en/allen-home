<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
 <script src="resources/js/jquery-1.8.3.js" type="text/javascript"></script>
  <script src="resources/js/report.js" type="text/javascript"></script>
<title>查看测试报告</title>
</head>
<body style="background-color:tan;">
	<div id="repTets" style="color: red;font-size: 35px;text-align: center;margin-left: auto;margin-right: auto;margin-top: 292px;">
	<span id="msg" style="display: block;" >${msg}</span><input id="bu" style="height: 35px; cursor: pointer;" onclick="startTest();" value="查看测试报告" type="button"></input>
	<span id="in" style="display: none;">正在查询中...请等待跳转！</span>
	</div>
</body>
</html>
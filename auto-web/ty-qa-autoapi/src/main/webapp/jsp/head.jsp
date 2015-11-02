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
</script>
</head>
<body>

	<input type="hidden" id="ctx" value="${ctx}"></input>
	<div class="header">
	<!-- Start Logo -->
	<div class="logo">
		<a href="http://www.tingyun.com" target="_blank"> 
			<img src="${ctx}/resources/img/logo.png" />
		</a>
	</div>
	<div class="zhiliang" id="zhiliang">
	<span>
		<a href="jsp/app/appHighChar.jsp">质量看板</a>
		<i class="icon-chevron-down icon-white">
		</i>
	</span>
		<ul id="another_project" class="another_project" style="display: none;">
			<li>
				<a id="appHigh" target="_blank" href="${ctx}/ApihighChar/getAppHighChar" id="another_link" >APP</a>
			</li>
			<li>
				<a id="another_link" href="/report/network/network/overview">SERVER</a>
			</li>
			<li>
				<a id="another_link" href="/report/network/network/overview">NET_WORK</a>
			</li>
		</ul>
	</div>
	<div class="head_title">
		<p>
		接  口  自  动  化  测  试  平  台
		</p>
	</div>
	<!-- End Logo -->
	<!-- Start meum -->
</div>
</body>
</html>
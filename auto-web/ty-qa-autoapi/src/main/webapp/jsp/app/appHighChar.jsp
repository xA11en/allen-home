<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>report用例展示jsp</title>
</head>
<body>

 
<%@include file="/jsp/head.jsp"%>
<div class="content_sidebar content_sidebar_background">	
<!-- <div class="sidebar"> -->
    
<!--     <ul> -->
<!--           <li> -->
<!--                	<a> -->
<!-- 		          <div class="app" name="APP概览" noUrl=""  > -->
<!-- 		          	错误接口报表 -->
<!-- 		           </div> -->
<!-- 		        </a> -->
<!--          </li> -->
<!--     </ul> -->
<!-- </div> -->
<div>
	<div class="button_add_go">
		<span class="icon_help_black basedonTip" data-hasqtip="14" oldtitle="展示各交互性能分类在图表时间段内所消耗的平均执行时间随时间变化的趋势" title="" aria-describedby="qtip-14">
		前20条
		</span>
	</div>
	
	    <div id="container" style="min-width:800px;height:400px;"></div>
         
</div>
</div>

</body>
</html>
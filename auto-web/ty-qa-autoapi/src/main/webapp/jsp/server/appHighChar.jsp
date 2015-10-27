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
<div class="sidebar">
    
    <ul>
          <li>
               	<a href="list.do">
		          <div class="app" name="APP概览" noUrl=""  >
		          	APP接口
		           </div>
		        </a>
         </li>
        <li>
        		<a href="/report/overview/mobile">
		          <div class="server" name="APP概览" noUrl=""  >
		          APP概览12
		           </div>
		        </a>
        </li>
<!--          <li id=current  > -->
<!--                <a href="/report/dashBoard/mobile"> -->
<!-- 		          <div class="icon_dashBoard_03" name="仪表盘" noUrl=""  > -->
<!-- 		                                        仪表盘 -->
<!-- 		           </div> -->
<!-- 		       </a> -->
<!--          </li> -->
<!--          <li> -->
<!--                <a href="/report/mobileApp/1/alarmList"> -->
<!-- 		           <div class="icon_appalert_01" name="APP警报" noUrl=""  > -->
<!-- 		                                        APP警报 -->
<!-- 		            </div> -->
<!-- 		       </a> -->
<!--           </li> -->
<!--           <li> -->
<!--                 <a href="/report/keyUrl/228/keyAlarmList"> -->
<!-- 		            <div class="icon_keyAlarmList_01" name="关键元素警报" noUrl=""  > -->
<!-- 		                                        关键元素警报 -->
<!-- 		            </div> -->
<!-- 		         </a> -->
<!--           </li>               -->
    </ul>
</div>
<div>
	<div class="button_add_go">
		
		<input  id="searchName" placeholder="   请输入caseName进行搜索"  class="changeUrl" type="text" onkeyup="getApiListByName();" 
		name="name" style="width: 200px;float:none;height:25px;margin-right:20px;background:url('resources/img/search.gif') no-repeat 5px 8px;" />
	 	<a target="_blank" href="go.do" onclick="alert('这个测试可能几秒时间，耐心等待！');">
			执行测试
		</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a type="button" href="addReport.do" >
			增  加
		</a>
		&nbsp;  &nbsp;	   &nbsp;         &nbsp;
	</div>
         
</div>
</div>

</body>
</html>
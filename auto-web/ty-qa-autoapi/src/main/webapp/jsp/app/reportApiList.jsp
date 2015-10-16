<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>report用例展示jsp</title>
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
<link href="resources/css/head.css" rel="stylesheet" type="text/css"/>
<!--  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"> -->
<script src="resources/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="resources/js/report.js" type="text/javascript"></script>
</head>
<body>



<%@include file="head.jsp"%>
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
		
		<input  id="searchName" placeholder="请输入caseName进行搜索"  class="changeUrl" type="text" onkeyup="getApiListByName();" 
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
         
	<div id="api_table" style="text-align: center; MARGIN-LEFT: auto;">
		<div style="padding-top:20px; height:765px; width: 1200px; text-align: center;background:none repeat scroll 0 0 rgb(238, 239, 243);  MARGIN-LEFT: auto;">
			<p class="p_span_time">
				<span>1</span>
			</p>
			<table id="json" border="1" width="100%" cellspacing="0" cellpadding="0" url="/result">
				<tbody>
					<tr style="height: 44px;">
					    <td class="class_td1">seq</td>
					    <td class="class_td1">case_name</td>
					    <td class="class_td1">authKey</td>
					    <td class="class_td1">parameter</td>
					    <td class="class_td1">url</td>
	<!-- 				    <td class="class_td1">xml</td> -->
					    <td class="class_td1">json</td>
					    <td class="class_td1" >operation</td>
					</tr>
					  <c:forEach var="listApis"  items="${listApis}" varStatus="status"
	                          >  
					<tr style="height: 40px;"  onmouseout="this.style.backgroundColor='#F4F9FD'" onmouseover="this.style.backgroundColor='rgb(29, 149, 200)'" >
						<td class="class_td"><c:out value="${status.count+((pages-1)*15)}" /></td>
					    <td class="class_td"><span><c:out value="${listApis.caseName}" /></span></td>
					    <td class="class_td"><c:out value="${listApis.c6nnnfcg}" /></td>
					    <td class="class_td"><span><c:out value="${listApis.parameter}" /></span></td>
					    <td class="class_td"><span><c:out value="${listApis.url}" /></span></td>
					    <td class="class_td"><a class="class_a" ><span><c:out value="${listApis.json}" /></span></a></td>
	<%-- 				    <td class="class_td"> <a class="class_a" href="detailJson0.do?id=${listApis.id}"><span><c:out value="${listApis.xml}" /></span></a></td> --%>
					    <td >
							 <a class="class_a" href="load.do?id=${listApis.id}">编辑</a>
	        				 <a class="class_a" href="del.do?id=${listApis.id}" onclick="return confirm('确定删除${listApis.caseName}吗?');">删除</a>
						</td>
					</tr>
						</c:forEach>
				</tbody>
			</table>
			<div class="class_page">
							<c:choose>
								<c:when test="${pages == 1}">
									<a ></a>
								</c:when>
								<c:otherwise>
									<a href="list.do?pages=1">首页</a>
								</c:otherwise>
							</c:choose>
			
							<c:choose>
								<c:when test="${pages > 1}">
									<a href="list.do?pages=${pages-1}">上一页</a>
								</c:when>
								<c:otherwise>
									上一页
								</c:otherwise>
							</c:choose>
							第${pages}页
							<c:choose>
								<c:when test="${pages < totalPages}">
									<a href="list.do?pages=${pages+1}">下一页</a>
								</c:when>
								<c:otherwise>
									下一页
								</c:otherwise>
							</c:choose>
							总共${totalPages}页
							<c:choose>
								<c:when test="${pages < totalPages}">
									<a href="list.do?pages=${totalPages}">尾页</a>
								</c:when>
							</c:choose>
			</div>
		</div>
	
		 
	
	</div>
</div>
</div>

</body>
</html>
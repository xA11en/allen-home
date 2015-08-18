<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>report用例展示jsp</title>
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
 <script src="resources/js/jquery-1.8.3.js" type="text/javascript"></script>
  <script src="resources/js/report.js" type="text/javascript"></script>
</head>
<body style="background-color: black;">
<div style="text-align: center; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
<h1 class="class_titlelist">
	<div class="class_ty">
	<span class="class_span">report 接 口 自 动 化 测 试 界 面<span>
	</div>
</h1>
	<div style="padding-top:20px; height:1000px; width: 1200px; text-align: center;background:none repeat scroll 0 0 rgb(238, 239, 243); MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	
	<form action="go.do">
					<input onclick="return confirm('即将在后台执行测试这可能需要一点时间...耐心等待界面跳转查看报告！不执行请点击《取消》')" class="class_button" type="submit" value="执行测试">
	</form>
	
	<input type="button" class="class_button" value="增  加" onclick="window.location.href ='addReport.do'">
		<table id="json" border="1" width="100%" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 44px;">
				    <td class="class_td1">序号</td>
				    <td class="class_td1">case_name</td>
				    <td class="class_td1">c6nnnfcg</td>
				    <td class="class_td1">参数</td>
				    <td class="class_td1">url</td>
				    <td class="class_td1">xml</td>
				    <td class="class_td1">json</td>
				    <td class="class_td1" >状态</td>
				</tr>
				  <c:forEach var="listApis"  items="${listApis}" varStatus="status"
                          >  
				<tr style="height: 40px;"  onmouseout="this.style.backgroundColor='#F4F9FD'" onmouseover="this.style.backgroundColor='rgb(29, 149, 200)'" >
					<td class="class_td"><c:out value="${status.count+((pages-1)*20)}" /></td>
				    <td class="class_td"><span><c:out value="${listApis.caseName}" /></span></td>
				    <td class="class_td"><c:out value="${listApis.c6nnnfcg}" /></td>
				    <td class="class_td"><span><c:out value="${listApis.parameter}" /></span></td>
				    <td class="class_td"><span><c:out value="${listApis.url}" /></span></td>
				    <td class="class_td"><span><c:out value="${listApis.xml}" /></span></td>
				    <td class="class_td"><span><c:out value="${listApis.json}" /></span></td>
<%-- 				     <td class="class_td"><c:out value="${reportApis.st}" /></td> --%>
				    <td >
						
						<c:choose>
							<c:when test="${not empty listApis.xml}">
								 <a class="class_a" href="detailXml.do?id=${listApis.id}">xml</a>
							</c:when>
							<c:otherwise>
								 <a class="class_a"></a>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${not empty listApis.json}">
								  <a class="class_a" href="detailJson.do?id=${listApis.id}">json</a>
							</c:when>
							<c:otherwise>
								<a class="class_a"></a>
							</c:otherwise>
						</c:choose>
						
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
	<div class="footlist">
		
			<ul class="pic">
				<li ></li> 
				<li ></li> 
				<li ></li> 	
			</ul>
			
			
	</div>
	 

</div>
</body>
</html>
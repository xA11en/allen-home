<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>report用例展示jsp</title>
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
 <script src="resources/js/jquery-1.8.3.min.js" type="text/javascript"></script>
  <script src="resources/js/report.js" type="text/javascript"></script>
</head>
<body>
<div style="text-align: center; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
<h1 class="class_titlelist">
	<span class="class_span">report接口自动化测试界面<span>
</h1>
	<div style="padding-top:20px; height:1000px; width: 1200px; text-align: center;background:none repeat scroll 0 0 rgb(238, 239, 243); MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	<input type="button" class="class_button" value="增  加" onclick="window.location.href ='addReportApi.jsp'">
		<table id="json" border="1" width="100%" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
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
				<tr>
				
					<td class="class_td"><c:out value="${status.index+1}" /></td>
				    <td class="class_td"><span id="name_span"><c:out value="${listApis.caseName}" /></span></td>
				    <td class="class_td"><c:out value="${listApis.c6nnnfcg}" /></td>
				    <td class="class_td"><span id="par_span"><c:out value="${listApis.parameter}" /></span></td>
				    <td class="class_td"><span id="url_span"><c:out value="${listApis.url}" /></span></td>
				    <td class="class_td"><span id="xml"><c:out value="${listApis.xml}" /></span></td>
				    <td class="class_td"><span id="json_span"><c:out value="${listApis.json}" /></span></td>
<%-- 				     <td class="class_td"><c:out value="${reportApis.st}" /></td> --%>
				    <td >
<%-- 				    href="${pageContext.request.contextPath}/person/toupdate.action?id=${p.id} --%>
<%-- 						<a href="detailXml.do?id=${listApis.id}">xml</a> --%>
<%-- 						<a href="detailJson.do?id=${listApis.id}">json</a> --%>
						 <a class="class_a" href="detailXml.do?id=${listApis.id}">xml</a>
						 <a class="class_a" href="detailJson.do?id=${listApis.id}">json</a>
						 <a class="class_a" href="load.do?id=${listApis.id}">编辑</a>
        				 <a href="del.do?id=${listApis.id}" onclick="return confirm('确定删除${listApis.caseName}吗?');">删除</a>
					</td>
				</tr>
					</c:forEach>
			</tbody>
		</table>
		<div class="class_page">
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
		</div>
	</div>
	<div class="footlist">
		<div class="pic">    有事吱声！  </div>
	</div>
</div>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<title>report用例展示jsp</title>
</head>
<body>



<%@include file="/jsp/head.jsp"%>
<div class="content_sidebar content_sidebar_background">	
<div class="sidebar">
    
    <ul>
          <li>
               	<a href="list.do">
		          <div class="app" name="" noUrl=""  >
		          	<span>APP接口</span>
		           </div>
		        </a>
         </li>
        <li>
        		<a href="listServer">
		          <div class="server" name="" noUrl=""  >
		          <span>SERVER接口</span>
		           </div>
		        </a>
        </li>
	    <li>
	        	<a href="listNetwork">
			       <div class="network" name="" noUrl=""  >
			        <span>NET_WORK接口</span>
			        </div>
			     </a>
	      </li>
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
		<span>server接口用例详细</span>
		<input  id="searchName" placeholder="   请输入caseName进行搜索"  class="changeUrl" type="text" onkeyup="getApiListByNameServer();" 
		name="name" style="width: 200px;float:none;height:25px;margin-right:20px;background:url('resources/img/search.gif') no-repeat 5px 8px;" />
	 	<a target="_blank" href="#" onclick="alert('这个测试可能几秒时间，耐心等待！');">
			执行测试
		</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a type="button" href="addServerReport.do" >
			增  加
		</a>
		&nbsp;  &nbsp;	   &nbsp;         &nbsp;
	</div>
         
	<div id="api_table" style="text-align: center; MARGIN-LEFT: auto;">
		<div style="padding-top:20px; height:765px; width:  84.5%; text-align: center;background:none repeat scroll 0 0 rgb(238, 239, 243);  MARGIN-LEFT: auto;">
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
					    <td class="class_td"><c:out value="${listApis.authKey}" /></td>
					    <td class="class_td"><span><c:out value="${listApis.parameter}" /></span></td>
					    <td class="class_td"><span><c:out value="${listApis.url}" /></span></td>
					    <td class="class_td"><a class="class_a" ><span><c:out value="${listApis.json}" /></span></a></td>
	<%-- 				    <td class="class_td"> <a class="class_a" href="detailJson0.do?id=${listApis.id}"><span><c:out value="${listApis.xml}" /></span></a></td> --%>
					    <td >
							 <a class="class_a" href="loadServer.do?id=${listApis.id}">编辑</a>
	        				 <a class="class_a" href="delServer.do?id=${listApis.id}" onclick="return confirm('确定删除${listApis.caseName}吗?');">删除</a>
						</td>
					</tr>
						</c:forEach>
					<tr style="height:40px;">
						 <td  rowspan="7"  colspan="7" style="color: red;" >${info}</td>
					</tr>
				</tbody>
			</table>
			<div class="class_page">
							<c:choose>
								<c:when test="${pages == 1}">
									<a ></a>
								</c:when>
								<c:otherwise>
									<a href="listServer.do?pages=1">首页</a>
								</c:otherwise>
							</c:choose>
			
							<c:choose>
								<c:when test="${pages > 1}">
									<a href="listServer.do?pages=${pages-1}">上一页</a>
								</c:when>
								<c:otherwise>
									上一页
								</c:otherwise>
							</c:choose>
							第${pages}页
							<c:choose>
								<c:when test="${pages < totalPages}">
									<a href="listServer.do?pages=${pages+1}">下一页</a>
								</c:when>
								<c:otherwise>
									下一页
								</c:otherwise>
							</c:choose>
							总共${totalPages}页
							<c:choose>
								<c:when test="${pages < totalPages}">
									<a href="listServer.do?pages=${totalPages}">尾页</a>
								</c:when>
							</c:choose>
			</div>
		</div>
	
		 
	
	</div>
</div>
</div>

</body>
</html>
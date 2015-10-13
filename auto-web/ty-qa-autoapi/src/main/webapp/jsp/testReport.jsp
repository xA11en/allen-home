<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
<link href="resources/css/testReport.css" rel="stylesheet" type="text/css" />
<script src="resources/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="resources/js/report.js" type="text/javascript"></script>
<title>测试报告</title>
</head>
<body style="background-color: #003838;">
	<div style="height:auto; width: 1000px;margin-top:80px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;background:none repeat scroll 0 0 rgb(238, 239, 243); ">
		<div class="center_right_box">
	<div class="subuser_box">
		<div class="subuser_tit">
	    	<div class="float_left">接 口 测 试 报 告
	    			<p class="p_return_class">
						<span onclick="location.href='list.do'">返回</span>
					</p>
	    	</div>
	    </div>
	    
	    <div class="subuser_layout">
	    	<div class="mytable">
		    	<div>
		            <table cellpadding="0" cellspacing="0" width="100%">
		                <tr class="trbj">
		                	<td class="td0">用例名称</td>
		                    <td class="td1">返回值</td>
		                    <td class="td2">请求头</td>
		                    <td class="td3">状态</td>
<!-- 		                    <td class="td4">角色</td> -->
<!-- 		                    <td class="td5">创建时间</td> -->
<!-- 							<td rowspan="3" class="td_rowspan">操作</td> -->
		                </tr>
		            </table>
		        </div>
		   		<div id="userInfo">
					<div class='p_div' id='page_1'>
						<div class='div_bj2'>
					<table cellpadding='0' cellspacing='0' width='100%'>
						<tr onmouseout="this.style.backgroundColor='#F4F9FD'" onmouseover="this.style.backgroundColor='#95EFF3'">
							<td class='td0'><span>${caseName}</span></td>
							<td class='td1' >
								<a class="class_a" onclick="location.href='apiResult.do'"><span id="api">查看详细返回信息</span></a>
							</td>
							<td class='td2'><span>${requestTou}</span></td>
							<td class='td3'>${Fail}</td>
						</tr>
						<tr> 
							<td class="td4"><span>${success}</span></td>
						</tr>
					</table>
						</div>
					</div>
				</div>
			</div>
	    </div>
	   
	    <form  id="queryForm" action="/lens-saas/subUserManager/userList" method="post">
		    <input type="hidden" name="query" value="query"/>
		    <input type="hidden" id="pageSize" name="pageSize"/>
	    </form>
    </div>
</div>
	</div>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用例jsp</title>
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color: black;">
	<div style="height:600px;;; width: 1000px;margin-top: 20px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;background:none repeat scroll 0 0 rgb(238, 239, 243); ">
	
	<div class="upd_class"><h2 style="text-align: center;line-height: 90px;color: blue">修 改 测 试 用 例 界 面</h2></div>	
	<form class="upd_clss_form"  action="update.do?id=${e.id}" method="post">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="class_td2" valign="middle" align="right">
									id:
								</td>
								<td class="class_td2"  valign="middle" align="left">
									${e.id}
								</td>
							</tr>
							<tr>
								<td class="class_td2"  valign="middle" align="right">
									用例名称:
								</td>
								<td class="class_td2"  valign="middle" align="left">
									<textarea type="text" class="inputgri"  
									name="name">${e.caseName}</textarea>
								</td>
							</tr>
							<tr>
								<td class="class_td2" valign="middle" align="right">
									c6nnnfcg:
								</td>
								<td class="class_td2" valign="middle" align="left">
									<input type="text" class="inputgri"  
									name="c6nnnfcg" value="${e.c6nnnfcg}"></input>
								</td>
							</tr>
							<tr>
								<td class="class_td2" valign="middle" align="right">
									参数:
								</td>
								<td class="class_td2" valign="middle" align="left">
									<textarea type="text" class="inputgri"  
									name="canshu">${e.parameter}</textarea>
								</td>
							</tr>
								<tr>
								<td class="class_td2" valign="middle" align="right">
									用例url:
								</td>
								<td class="class_td2" valign="middle" align="left">
									<textarea type="text" style="background:  none repeat scroll 0 0 rgb(238, 238, 238);
		border: 1px solid #686868; width: 800px;height: 50px;"  
									name="url">${e.url}</textarea>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="class_button" 
							value="保 存" />
						</p>
					</form>
	
	</div>
	<div class="foot">
			<ur class="pic">
				<li ></li> 
				<li ></li> 
				<li ></li> 	
			</ur>
			
	</div>
</body>
</html>
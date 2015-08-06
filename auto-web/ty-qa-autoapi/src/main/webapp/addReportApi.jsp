<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用例jsp</title>
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
<script src="resources/js/jquery-1.8.3.min.js" sype="text/javascript"></script>
</head>
<body>
	<script>
		function submitt(){
			var name = $("#name").val();
			var canshu = $("#canshu").val();
			var url = $("#url").val();
			if(name ==null || name==""){
				alert("请输入测试用例名称：");
				return false;
			}
			if(canshu ==null || canshu==""){
				alert("请输入参数名称：");
				return false;
			}
			if(url ==null || url==""){
				alert("请输入url名称：");
				return false;
			}
			
			$("#for_submit").submit();
		}
	
	</script>


	<div style="height:600px;;; width: 1000px;margin-top: 20px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;background:none repeat scroll 0 0 rgb(238, 239, 243); ">
	
	<div class="upd_class"><h2 style="text-align: center;line-height: 90px;">report增加jsp</h2></div>	
	<form id="for_submit" class="upd_clss_form" action="add.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="class_td2"  valign="middle" align="right">
									用例名称:
								</td>
								<td class="class_td2"  valign="middle" align="left">
									<textarea id="name" type="text" class="inputgri"  
									name="name"></textarea>
								</td>
							</tr>
							<tr>
								<td class="class_td2" valign="middle" align="right">
									c6nnnfcg:
								</td>
								<td class="class_td2" valign="middle" align="left">
									<input ype="text" class="inputgri" 
									name="c6nnnfcg"/>
								</td>
							</tr>
							<tr>
								<td class="class_td2" valign="middle" align="right">
									参数:
								</td>
								<td class="class_td2" valign="middle" align="left">
									<textarea type="text" class="inputgri"  id="canshu"
									name="canshu"></textarea>
								</td>
							</tr>
								<tr>
								<td class="class_td2" valign="middle" align="right">
									用例url:
								</td>
								<td class="class_td2" valign="middle" align="left">
									
									<textarea type="text" class="inputgri" id="url"
									name="url" ></textarea>
								</td>
							</tr>
						</table>
						<p>
							<input type="button" class="class_button" 
							value="保 存" onclick="submitt();"/>
						</p>
					</form>
	
	</div>
	<div class="foot">
		<div class="pic">    有事吱声！  </div>
	</div>
</body>
</html>
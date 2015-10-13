<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
<script src="resources/js/jquery-1.8.3.js" type="text/javascript"></script>
<title>XML/JSON 详 细 情 况 界 面 </title>
</head>
<body style="background-color: black;">
	<div style="height:auto; width: 1000px;margin-top: 20px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;background:none repeat scroll 0 0 rgb(238, 239, 243); ">
	<div class="upd_class">
		<h2 style="text-align: center;line-height: 90px;">
			<span style="color: blue"> JSON 详 细 情 况 界 面 </span>
		</h2>
	</div>	
    	<div>
		      <table cellpadding="0" cellspacing="0" width="100%">
		                <tr class="trbj">
		                	<td class="td0">原返回值</td>
		                    <td class="td1">错误返回值</td>
		                </tr>
		                 <tr class="trbj">
		                	<td class="td0">
			                	<div style="margin: 0px; width: 470px; height: 430px;">
			                	<textarea style="width: 488px; height: 420px;">
			                     ${appJson}
			                    </textarea>
<!-- 			                    	<iframe> -->
<!-- 				                    	<div> -->
<%-- 				                    	${appJson} --%>
<!-- 				                    	</div> -->
<!-- 			                    	</iframe> -->
			                    </div>
		                	</td>
		                    <td class="td1">
			                    <div style="margin: 0px; width: 470px; height: 430px;">
			                    <textarea style="width: 484px; height: 420px;">
			                     ${errorJson}
			                    </textarea>
<!-- 			                    	<iframe> -->
<!-- 			                    		<div> -->
<%-- 					                    ${errorJson} --%>
<!-- 			                    		</div> -->
<!-- 			                    	</iframe> -->
			                    </div>
		                    </td>
		                </tr>
		       </table>
		 </div>
	</div>
</body>
</html>
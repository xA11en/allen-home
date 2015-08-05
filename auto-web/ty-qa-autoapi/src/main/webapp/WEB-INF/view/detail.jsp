<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/reportApi.css" rel="stylesheet" type="text/css" />
<script src="resources/js/jquery-1.8.3.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="height:600px; width: 1000px;margin-top: 20px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;background:none repeat scroll 0 0 rgb(238, 239, 243); ">
	<div class="upd_class">
		<h2 style="text-align: center;line-height: 90px;">
			<span/></span> Detail jsp
			
		</h2>
	</div>	
	<textarea class="upd_clss_form">
		${listString}
	</textarea>
	</div>
	<div class="foot">
		<div class="pic">    有事吱声！  </div>
	</div>
</body>
</html>
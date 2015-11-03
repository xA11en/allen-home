<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<script src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>
<%-- <script src="${ctx}/resources/js/highcharts.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/resources/js/jquery-1.8.3.js" type="text/javascript"></script> --%>
<title>app接口报表展示</title>
<script type="text/javascript">
$(function () { 
// 	var path = $("#ctx").val();
// 	var appPath = ${ctx}/ApihighChar/errorCount;
	var x = [];//X轴
	var y = [];//Y轴
	var color = ["gray","pink","red","blue","yellow","green","#fff"];
		$.ajax({
			type:"post",
			url:"${ctx}/ApihighChar/errorCount",
			success:function(data){
				 var obj = eval("("+data+")");  
				 for(var key in obj){ 
					 var value = obj[key];
					 for(var name in value){
						 //获取后台的key 和value
						// alert(name);
						 x.push(name);
						 y.push(value[name]);
					 }
					} 
				 
				 var chart = new Highcharts.Chart({
				        chart:{
				            renderTo:'container',
				            type:'line' //显示类型 柱形
				        },
				        title:{
				            text:'app前20接口错误' //图表的标题
				        },
				        xAxis:{
				            categories:x,
				            labels:{
				            	rotation:45,
				            	style:{
				            		color:"rgb(124, 181, 236)"	
				            	}
				            	
				            },
				        },
				        yAxis:{
				        	categories:y,
				            title:{
				                text:'接口错误次数' //Y轴的名称
				            },
				            labels:{
				            	style:{
				            		color:"black",
				            		font:"25px"
				            		
				            	}
				            	
				            },
				        },
				        series:[{
				            name:"错误次数",
				            data:y,
				            marker:{
				            	symbol:"circle",
				            	fillColor:"#ffffff",
				            	lineColor:null,
				            	lineWidth:2.5,
				            	radius:4
				            	}
				        }]
				    });
			}
		,error:function(){
			alert("网络错误！");
		}
		});
		
});
</script>
</head>
<body style="background: #6699FF;">


	<%@include file="/jsp/head.jsp"%>
	<div class="content_sidebar content_sidebar_background">
		<div>
			<div class="button_add_go">
				<span class="icon_help_black" data-hasqtip="14"
					oldtitle="展示各交互性能分类在图表时间段内所消耗的平均执行时间随时间变化的趋势" title=""
					aria-describedby="qtip-14"> app前20条错误接口展示 </span>
			</div>
			<div id="container"
				style="width: auto; height: auto; text-align: center;  margin-top: 110px;"></div>
		</div>
	</div>

</body>
</html>
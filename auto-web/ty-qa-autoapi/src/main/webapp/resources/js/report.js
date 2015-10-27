jQuery(function(){ 
		$("#json tr td span").each(function(){ 
			//获取td当前对象的文本,如果长度大于25;  
			if($(this).text().length>20){  
				//给td设置title属性,并且设置td的完整值.给title属性.
				$(this).attr("style","cursor:pointer");  
				$(this).attr("title",$(this).text());  
				//获取td的值,进行截取。赋值给text变量保存.  
				var text=$(this).text().substring(0,20)+"...";  
				//重新为td赋值;  
				$(this).text(text);
			}
			
		});  
});  

jQuery(function(){ 
	$("#api").each(function(){ 
		//获取td当前对象的文本,如果长度大于25;  
		if($(this).text().length>40){  
			//给td设置title属性,并且设置td的完整值.给title属性.
			$(this).attr("style","cursor:pointer");  
			$(this).attr("title",$(this).text());  
			//获取td的值,进行截取。赋值给text变量保存.  
			var text=$(this).text().substring(0,40)+"...";  
			//重新为td赋值;  
			$(this).text(text);
		}
		
	});  
});  

function cliImg(){
	if($("#optionDiv").css("display")=="none"){
		$("#optionDiv").show(500); 
	}else{
		$("#optionDiv").hide(500); 
	}
	
}
function clixml(id,caseName){
	$.ajax({
		type:"get",
		url:"del.do?id="+id,
		success:function(data){
			console.log(data);
			if(data && $.trim(data)=="success"){
				confirm("确定要删除'"+caseName+"'吗？'");
				window.location.href ="list.do";
			}else{
				alert("删除失败！");
			}
		}
	,error:function(data){
		alert("网络异常！");
	}
	});
}

var time=0;
var timeout=60;
function startTestxx(){
	$("#msg").hide(); 
	$("#bu").hide(); 
	$("#in").show();
	$("#num").show();
	time++;
	document.getElementById("num").innerHTML=time;
	if(time==timeout){
		alert("超过最大时间,点击确定跳转回首页，重新执行！");
	    window.location.href ="list.do";
	}
	$.ajax({
		type:"post",
		url:"start.do",
		success:function(data){
			if(data && $.trim(data.msg)=="success"){
				time=0;
				clearInterval("startTest()");
				window.location.href ="html/index.html";
			}
		}
	});
	setInterval("startTest()",5000)
}
/**
 * app server network 搜索
 */

function getApiListByName(){
	var name = $.trim($("#searchName").val());
	if(name=="" || name==null){
		window.location.href ="list.do";
	}
	if(name.length>30){
		alert("超过查询字符限制！");
	}
	 $("#api_table").load("search.do?caseName=" + encodeURI(encodeURI(name)), false);
}
function getApiListByNameServer(){
	var name = $.trim($("#searchName").val());
	if(name=="" || name==null){
		window.location.href ="listServer";
	}
	if(name.length>30){
		alert("超过查询字符限制！");
	}
	 $("#api_table").load("searchServer.do?caseName=" + encodeURI(encodeURI(name)), false);
}
function getApiListByNameNet(){
	var name = $.trim($("#searchName").val());
	if(name=="" || name==null){
		window.location.href ="listNetwork";
	}
	if(name.length>30){
		alert("超过查询字符限制！");
	}
	 $("#api_table").load("searchNetwork.do?caseName=" + encodeURI(encodeURI(name)), false);
}
/**
 * 质量看板 下拉菜单
 */
$(function(){
	$("#zhiliang").mouseover(function(){
		$("#zhiliang span").css("background","black");
		$("#another_project").show();
	});
	$("#zhiliang").mouseout(function(){
		$("#zhiliang span").css("background","none");
		$("#another_project").hide();
	});
});
/**
 * highchar app
 */
$(function(){
	var path = $("#ctx").val();
	var appPath = path+"/ApihighChar/errorCount";
	var x = [];//X轴
	var y = [];//Y轴
	var color = ["gray","pink","red","blue","yellow","green","#fff"];
		$.ajax({
			type:"post",
			url:appPath,
			success:function(data){
				 var obj = eval("("+data+")");  
				 for(var key in obj){ 
					 var value = obj[key];
					 for(var name in value){
						 //获取后台的key 和value
						// alert(name);
						 x.push(name);
						 y.push(value[name]);
						 var chart = new Highcharts.Chart({
						        chart:{
						            renderTo:'container',
						            type:'column' //显示类型 柱形
						        },
						        title:{
						            text:'app前20接口错误' //图表的标题
						        },
						        xAxis:{
						            categories:x
						        },
						        yAxis:{
						        	categories:y,
						            title:{
						                text:'错误次数' //Y轴的名称
						            },
						        },
						        series:[{
						            name:"姓名",
						            data:value
						        }]
						    });
					 }
					}  
			}
		,error:function(){
			alert("网络错误！");
		}
		});

	
});


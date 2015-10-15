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
			alert(data);
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


function getApiListByName(){
	var name = $("#searchName").val();
	if(name=="" || name==null){
		window.location.href ="list.do";
	}
//	alert(name);
//	$.ajax({
//		type:"get",
//		url:"search.do?caseName="+name,
//		success:function(data){
//			alert(data.length);
//			alert(2);
//			if(data){
//				alert(data.length);
//			}
//		}
//	});
	 $("#api_table").load("search.do?caseName=" + encodeURI(encodeURI($("#searchName").val())), false);
}

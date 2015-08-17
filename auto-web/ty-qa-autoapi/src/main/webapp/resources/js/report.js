jQuery(function(){ 
		$("#json tr td span").each(function(){ 
			//获取td当前对象的文本,如果长度大于25;  
			if($(this).text().length>10){  
				//给td设置title属性,并且设置td的完整值.给title属性.
				$(this).attr("style","cursor:pointer");  
				$(this).attr("title",$(this).text());  
				//获取td的值,进行截取。赋值给text变量保存.  
				var text=$(this).text().substring(0,10)+"...";  
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
			if(data && $.trim(data.msg)=="success"){
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


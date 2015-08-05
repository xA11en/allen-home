jQuery(function(){ 
	var arr = new Array("#name_span","#url_span","#par_span","#json_span","#xml");
	for(var i=0;i<arr.length;i++){
		$(arr[i]).each(function(){ 
			//获取td当前对象的文本,如果长度大于25;  
			if($(this).text().length>10){  
				//给td设置title属性,并且设置td的完整值.给title属性.  
				$(this).attr("title",$(this).text());  
				//获取td的值,进行截取。赋值给text变量保存.  
				var text=$(this).text().substring(0,10)+"...";  
				//重新为td赋值;  
				$(this).text(text);  
			}  
		});  
	}
});  

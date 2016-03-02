package com.tingyun.alarm;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tingyun.alarm.entity.ProductLineType;
import com.tingyun.alarm.utils.HtmlMail;
import com.tingyun.alarm.utils.InitXml;
import com.tingyun.test.TestJobExecutor;

public  class MinorMain {
	
	
	
		public MinorMain(String flag){
			if(flag.equals(ProductLineType.APP)){
				List<String> confs = new  InitXml().getXML(flag);
				this.excuteTest(confs);
			}else if(flag.equals(ProductLineType.SERVER)){
				List<String> confs = new  InitXml().getXML(flag);
				this.excuteTest(confs);
			}
			}
		
		
		
		private void excuteTest(List<String> confs){
			ExecutorService service = Executors.newFixedThreadPool(confs.size()); 
			for (int i = 0; i <confs.size(); i++) {
				 final int taskId = i;
				 final String xmlName = confs.get(taskId);
			service.execute(new Runnable() { 
			public void run() {
				TestJobExecutor exec = new TestJobExecutor();
				exec.init(xmlName);
				exec.runTest();
			} 
			});
			}
			service.shutdown();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 while (true) {  
		            if (service.isTerminated()) {  
		            	HtmlMail.generateMailHtml(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\alarmResult.txt"));;
		            	System.out.println("报告已完成，邮件已发送！");
		                break;  
		            }  
		        } 
		}
		
		
	
}

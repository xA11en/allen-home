package com.tingyun.alarm;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tingyun.alarm.utils.HtmlMail;
import com.tingyun.alarm.utils.InitXml;
import com.tingyun.test.TestJobExecutor;

public class Main {
	

	public static void main(String[] args) {
		InitXml initXmli = new  InitXml();
		List<String> confs = initXmli.getXML();
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
		 while (true) {  
	            if (service.isTerminated()) {  
	            	HtmlMail.generateMailHtml(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\alarmResult.txt"));;  
	                break;  
	            }  
	        }  
		
	}

}

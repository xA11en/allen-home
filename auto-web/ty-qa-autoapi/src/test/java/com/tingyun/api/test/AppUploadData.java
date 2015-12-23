package com.tingyun.api.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tingyun.api.auto.entity.alarm.AppParmaters;
import com.tingyun.api.auto.service.alarm.MobileAgentSimulatorNew;

public class AppUploadData  {

//	private String key;
//	private String thinkTime;
//	private String mode;
//	private String count;
//	private String period;
//	private String responseTime;
//	private String interactionTime;
//	private String viewLoadTime;
//	private String imageProcessTime;
//	private String dataStorageTime;
//	private String networkVisitTime;
//	private String jsonProcessTime;
//	private String httpErrorPercentage;
//	private String networkErrorPercentage;
//	private String deviceIndex;
	
	public static List<AppParmaters> mainUpload() {
		List<AppParmaters> parmaters = new ArrayList<AppParmaters>();
		//在这里添加参数
		parmaters.add(new AppParmaters(
				"196c21138a5b496397806b06e3ca86bf","6000","fixed","10","5","200","51","51","51","51","51","51","0.0","0.0","1"));
		parmaters.add(new AppParmaters(
				"46ee0d1a497741eca1375feafdec074e","6000","fixed","10","5","200","51","51","51","51","51","51","0.0","0.0","2"));
		
		return parmaters;
	}
	

	public static void main(String[] args) {
		 final List<AppParmaters> parmaters = mainUpload();
		//创建固定大小的线程池
		ExecutorService service = Executors.newFixedThreadPool(parmaters.size()); 
		for (int i = 0; i <parmaters.size(); i++) {
			 final int taskId = i;
		service.execute(new Runnable() { 
		public void run() {
			new MobileAgentSimulatorNew().main(parmaters.get(taskId));
//			System.out.println("启动线程执行任务：  "+taskId);
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("end");
		} 
		});
		}
		service.shutdown();
	}

}


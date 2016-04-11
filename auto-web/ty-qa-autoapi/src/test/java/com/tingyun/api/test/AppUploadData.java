//package com.tingyun.api.test;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import com.tingyun.api.auto.entity.alarm.AppParmaters;
//
//public class AppUploadData  {
//
////	private String key;
////	private String thinkTime;
////	private String mode;
////	private String count;
////	private String period;
////	private String responseTime;
////	private String interactionTime;
////	private String viewLoadTime;
////	private String imageProcessTime;
////	private String dataStorageTime;
////	private String networkVisitTime;
////	private String jsonProcessTime;
////	private String httpErrorPercentage;
////	private String networkErrorPercentage;
////	private String deviceIndex;
//	
////	public static List<AppParmaters> mainUpload() {
////	
//////		List<AppParmaters> parmaters = new ArrayList<AppParmaters>();
//////		//在这里添加参数
//////		parmaters.add(new AppParmaters(
//////				"196c21138a5b496397806b06e3ca86bf","6000","fixed","10","1","200","51","51","51","51","51","51","0.0","0.0","1"));
//////		parmaters.add(new AppParmaters(
//////				"46ee0d1a497741eca1375feafdec074e","6000","fixed","10","1","200","51","51","51","51","51","51","0.0","0.0","2"));
//////		
//////		return parmaters;
////	}
//	
//
//	public static void main(String[] args) {
//		double d = 2.0/3.0;
//		System.out.println(round(d,2,BigDecimal.ROUND_HALF_UP));
//		} 
//		
//	public static double round(double value, int scale, int roundingMode) { 
//		BigDecimal bd = new BigDecimal(value); 
//		bd = bd.setScale(scale, roundingMode); 
//		double d = bd.doubleValue(); 
//		bd = null; 
//		return d; 
//	}
//	}
////		 final List<AppParmaters> parmaters = mainUpload();
////		//创建固定大小的线程池
////		ExecutorService service = Executors.newFixedThreadPool(parmaters.size()); 
////		for (int i = 0; i <parmaters.size(); i++) {
////			 final int taskId = i;
////		service.execute(new Runnable() { 
////		public void run() {
////			new MobileAgentSimulatorNew().main(parmaters.get(taskId));
//////			System.out.println("启动线程执行任务：  "+taskId);
//////			try {
//////				Thread.sleep(3000);
//////			} catch (InterruptedException e) {
//////				// TODO Auto-generated catch block
//////				e.printStackTrace();
//////			}
////			System.out.println("end");
////		} 
////		});
////		}
////		service.shutdown();
////		 while (true) {  
////	            if (service.isTerminated()) {  
////	                System.out.println("结束了！");  
////	                break;  
////	            }  
////	        }  
////	}
//
//

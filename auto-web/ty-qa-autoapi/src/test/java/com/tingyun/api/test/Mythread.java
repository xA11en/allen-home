package com.tingyun.api.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Mythread {
//	volatile boolean stop = false;
//	private int no;
//	public Mythread(int str){
//		no = str;
//	    }
//	public static int THREAD_NO_NOE = 0;
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//			if(0 == no){
//					System.out.println("no=0");
//			}
//			if(1==no){
//				System.out.println("no=1");
//			}
//	}
	public static void test1(){
		System.out.println("test1");
	}
	public static void test2(){
		System.out.println("test2");
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final Service s = new Service (); 
		ExecutorService service = Executors.newCachedThreadPool(); 
		for(int i=0; i<10; i++) { 
		service.execute(new Runnable() { 
		public void run() { 
		s.service(); 
		} 
		}); 
		} 
		} 
		static class Service { 
		private int count = 0; 
		Lock lock = new ReentrantLock(); 
		public void service() { 
		++ count; 
		try { 
		Thread.sleep(200l); 
		} catch (InterruptedException e) { 
		e.printStackTrace(); 
		} 
		System.out.println("service :" + count + " time"); 
		} 
		} 

//		ExecutorService service = Executors.newCachedThreadPool(); 
//		CompletionService<Integer> cservice = new ExecutorCompletionService<Integer>(service); 
//		Collection<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>(); 
//		for(int i=0; i<10; i++) { 
//		final int squence = i; 
//		tasks.add(new Callable<Integer>() { 
//		@Override 
//		public Integer call() throws Exception { 
//		for(int j=1; j<5; j++) {	
//			System.out.println(Thread.currentThread().getName() + " task " + squence + " time " + j); 
//		} 
//		return squence; 
//		} 
//		}); 
//		} 
//		for(Callable<Integer> task:tasks) { 
//		cservice.submit(task); 
//		} 
//		//统一处理任务结果 
//		int result = 0; 
//		for(int i=0; i<tasks.size(); i++) { 
//		result += cservice.take().get(); 
//		} 
//		System.out.println(result); 
//		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//			cachedThreadPool.execute(new Runnable() {
//				@Override
//				public void run() {
//					test1();
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					test2();
//				}
//			});
//		AppParmaters ap = new AppParmaters("100","fixed","2000","300","200","50","50","50","50","50","50","0.8","0.218");
//		System.out.println(ap.getCount());
//			 Mythread m = new Mythread(0);
//			 m.start();
//			 try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			 System.out.println("线程中之前："+m.getId());
//			 m.stop();
//			 System.out.println("线程中之后："+m.getId());
//			 Mythread m1 = new Mythread(1);
//			 m1.start();
	//}
//	public static void main(String[] args)throws ExecutionException,  
//    InterruptedException, java.util.concurrent.ExecutionException {
////		Mythread m = new Mythread();
////		Mythread m1 = new Mythread();
//		 System.out.println("----程序开始运行----");  
//		   Date date1 = new Date();  
//		  
//		   int taskSize = 5;  
//		   // 创建一个线程池  
//		   ExecutorService pool = Executors.newFixedThreadPool(taskSize);  
//		   // 创建多个有返回值的任务  
//		   List<Future> list = new ArrayList<Future>();  
//		   for (int i = 0; i < taskSize; i++) {  
//		    Callable c = new MyCallable(i + " ");  
//		    // 执行任务并获取Future对象  
//		    Future f = pool.submit(c);  
//		    // System.out.println(">>>" + f.get().toString());  
//		    list.add(f);  
//		   }  
//		   // 关闭线程池  
//		   pool.shutdown();  
//		  
//		   // 获取所有并发任务的运行结果  
//		   for (Future f : list) {  
//		    // 从Future对象上获取任务的返回值，并输出到控制台  
//		    System.out.println(">>>==" + f.get().toString());  
//		   }  
//		  
//		   Date date2 = new Date();  
//		   System.out.println("----程序结束运行----，程序运行时间【"  
//		     + (date2.getTime() - date1.getTime()) + "毫秒】");  
//	}
//	
//}
//class MyCallable implements Callable<Object> {  
//	private String taskNum;  
//	
//	MyCallable(String taskNum) {  
//		this.taskNum = taskNum;  
//	}
//
//	@Override
//	public Object call() throws Exception {
//		// TODO Auto-generated method stub
//		   System.out.println(">>>" + taskNum + "任务启动");  
//		   Date dateTmp1 = new Date();  
//		   Thread.sleep(1000);  
//		   Date dateTmp2 = new Date();  
//		   long time = dateTmp2.getTime() - dateTmp1.getTime();  
//		   System.out.println(">>>" + taskNum + "任务终止");  
//		   return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";  
//	}  
}
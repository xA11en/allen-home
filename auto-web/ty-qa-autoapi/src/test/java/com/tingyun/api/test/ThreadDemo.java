package com.tingyun.api.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;

public class ThreadDemo  {
	public static void main(String[] args) {
                new JUnitCore().run(Request.method(test.class, "testFindAll"));
   }
//
//	 public void run() {
//		  java.util.Timer timer = new java.util.Timer();
//		  timer.schedule(new TimerTask()
//		  {
//		   public void run()
//		   {
//		    new ThreadDemo().run();
//		   }
//		  },0,1000);
//		  
//		 }
//	 public static void main(String[] args) {
//			System.out.println(1);
//		}
}


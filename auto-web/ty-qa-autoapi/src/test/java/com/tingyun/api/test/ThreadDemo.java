package com.tingyun.api.test;

import java.util.Random;
import java.util.TimerTask;

public class ThreadDemo implements Runnable {
	

	 public void run() {
		  java.util.Timer timer = new java.util.Timer();
		  timer.schedule(new TimerTask()
		  {
		   public void run()
		   {
		    new ThreadDemo().run();
		   }
		  },0,1000);
		  
		 }
	 public static void main(String[] args) {
			System.out.println(1);
		}
}


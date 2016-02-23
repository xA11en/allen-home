package com.tingyun.test;

import java.util.HashMap;
import java.util.Map;

public class TestJobContext {

	private static ThreadLocal<TestJobContext> local = new ThreadLocal<TestJobContext>();
	private Map<String,Object> context = new HashMap<String,Object>();
	
	private TestJobContext(){}
	
	public static TestJobContext getContext(){
		TestJobContext singleton = local.get();
		if(singleton == null){
			local.set(new TestJobContext());
			singleton = local.get();
		}
		return singleton;
	}
	
	public void put(String key,Object value){
		this.context.put(key, value);
	}
	
	public Object get(String key){
		return this.context.get(key);
	}
	
	public static void main(String[] args) {
		//System.out.println(TestJobContext.getContext());
		
		for(int i=0;i<10;i++){
			Thread thread = new Thread(new TestJobContext.MyThread());
			thread.start();
		}
		
	}
	
	static class MyThread implements Runnable{
		public void run() {
			
			for(int i=0;i<5;i++){
				System.out.println(TestJobContext.getContext());
			}
		};
	}
}

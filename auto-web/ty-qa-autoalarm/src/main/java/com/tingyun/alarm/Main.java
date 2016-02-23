package com.tingyun.alarm;

import com.tingyun.test.TestJobExecutor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestJobExecutor exec = new TestJobExecutor();
		exec.init("conf/Test-Case-1.xml");
		
		Thread thread = new Thread(exec);
		thread.start();
	}

}

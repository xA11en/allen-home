package com.tingyun.alarm;

import com.tingyun.test.TestJobExecutor;

public class Main {
	
	public final static String [] confs = new String[]{
		"conf/Test-Case-1.xml"
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<confs.length;i++){
			TestJobExecutor exec = new TestJobExecutor();
			exec.init(confs[i]);
			Thread thread = new Thread(exec);
			thread.start();
		}
	}

}

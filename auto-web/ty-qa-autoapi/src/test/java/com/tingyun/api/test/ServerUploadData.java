package com.tingyun.api.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tingyun.api.auto.entity.alarm.ServerParmaters;
import com.tingyun.api.auto.service.alarm.ApplicationSimulatorWithFixValuesAAA;

public class ServerUploadData  {

	// private String ApplicationName;
	// private String mode;
	// private String count;
	// private String period;
	// private String apdex;
	// private String responseTime;
	// private String errRate;
	
	public static List<ServerParmaters> mainUpload() {
		List<ServerParmaters> parmaters = new ArrayList<ServerParmaters>();
		//在这里添加参数
		parmaters.add(new ServerParmaters(
				new String[] { "pyh-触发告警123" }, "fixed", "20", "1", "0.8","500", "0.0"));
		parmaters.add(new ServerParmaters(
				new String[] { "pyh-触发告警456" }, "fixed", "20", "1", "0.8","500", "0.0"));
		return parmaters;
	}
	

	public static void main(String[] args) {
		final List<ServerParmaters> parmaters = mainUpload();
		//开启一个线程池
		ExecutorService service = Executors.newFixedThreadPool(parmaters.size()); 
		for (int i = 0; i < parmaters.size(); i++) {
			final int taskId = i;
		service.execute(new Runnable() { 
		public void run() {
			new ApplicationSimulatorWithFixValuesAAA().main(new String[]{"https://dc1.networkbench.com","888-888-888"}, parmaters.get(taskId));
		} 
		});
		}
		
		service.shutdown();
	}

}


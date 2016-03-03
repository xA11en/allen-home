package com.tingyun.alarm.task;

import java.util.List;
import java.util.Map;

import com.tingyun.alarm.entity.AppParmaters;
import com.tingyun.alarm.entity.ServerParmaters;
import com.tingyun.alarm.service.ApplicationSimulatorWithFixValuesAAA;
import com.tingyun.alarm.service.MobileAgentSimulatorNew;
import com.tingyun.test.task.AbstractTestTask;

public class uploadServerTask extends AbstractTestTask {

	public uploadServerTask() {
	}

	@Override
	public List doTask() {
		
		
		Map<String,Object> params = this.params;
		String ApplicationName = (String)params.get("ApplicationName");
		String mode = (String)params.get("fixed");
		String count = (String)params.get("count");
		String period = (String)params.get("period");
		String apdex = (String)params.get("apdex");
		String responseTime = (String)params.get("responseTime");
		String errRate = (String)params.get("errRate");
		
		
		new ApplicationSimulatorWithFixValuesAAA().main(new String[]{"https://dc1.networkbench.com","888-888-888"},
				new ServerParmaters(
						new String[] { ApplicationName }, mode, count, period, apdex,responseTime, errRate));
		
		return null;
	}


}

package com.tingyun.alarm.task;

import java.util.List;
import java.util.Map;

import com.tingyun.alarm.entity.AppParmaters;
import com.tingyun.alarm.service.MobileAgentSimulatorNew;
import com.tingyun.test.task.AbstractTestTask;

public class uploadAppTask extends AbstractTestTask {

	public uploadAppTask() {
	}

	@Override
	public List doTask() {
		
		Map<String,Object> params = this.params;
		String key = (String)params.get("key");
		String thinkTime = (String)params.get("thinkTime");
		String fixed = (String)params.get("fixed");
		String count = (String)params.get("count");
		String period = (String)params.get("period");
		String responseTime = (String)params.get("responseTime");
		String interactionTime = (String)params.get("interactionTime");
		
		new MobileAgentSimulatorNew().main(new AppParmaters(
				key,thinkTime,fixed,count,period,responseTime,interactionTime,"51","51","51","51","51","0.0","0.0","1"));
		
		return null;
	}

}

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
		String viewLoadTime = (String)params.get("viewLoadTime");
		String imageProcessTime = (String)params.get("imageProcessTime");
		String dataStorageTime = (String)params.get("dataStorageTime");
		String networkVisitTime = (String)params.get("networkVisitTime");
		String jsonProcessTime = (String)params.get("jsonProcessTime");
		String httpErrorPercentage = (String)params.get("httpErrorPercentage");
		String networkErrorPercentage = (String)params.get("networkErrorPercentage");
		String deviceIndex = (String)params.get("deviceIndex");
		
		
		
		new MobileAgentSimulatorNew().main(new AppParmaters(
				key,thinkTime,fixed,count,period,responseTime,interactionTime,viewLoadTime,
				imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,httpErrorPercentage,networkErrorPercentage,
				deviceIndex));
		
		return null;
	}


}

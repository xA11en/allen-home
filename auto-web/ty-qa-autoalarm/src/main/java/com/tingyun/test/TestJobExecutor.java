package com.tingyun.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.test.builder.XmlConfigBuilder;

public class TestJobExecutor implements Runnable{
	private Logger logger = LoggerFactory.getLogger(TestJobExecutor.class);
	
	private TestJob testJob;

	@Override
	public void run() {
		if(testJob!=null){
			TestJobContext context = TestJobContext.getContext();
			for(TestStep step:testJob.getSteps()){
				if(step.getStartTimeExp()!=null){
					context.put(step.getStartTimeExp(), new Date());
				}
				
				for(TestTask task:step.getTasks()){
					if(task.getStartTimeExp()!=null){
						context.put(task.getStartTimeExp(), new Date());
					}
					
					//业务
					List result = task.doTask();
					if(result!=null){
						context.put(task.getName()+"_data", result);
					}
					
					if(task.getSleepTime()>0){
						try {
							Thread.sleep(task.getSleepTime());
							logger.info("task["+task.getName()+"] sleep "+step.getSleepTime()+" s");
						} catch (InterruptedException e) {
							logger.error("task["+task.getName()+"] sleep "+step.getSleepTime()+" s error.",e);
						}
					}
					if(task.getEndTimeExp()!=null){
						context.put(task.getEndTimeExp(), new Date());
					}
				}
				
				
				if(step.getSleepTime()>0){
					try {
						Thread.sleep(step.getSleepTime());
					} catch (InterruptedException e) {
						logger.error("step sleep "+step.getSleepTime()+" s error.",e);
					}
				}
				if(step.getEndTimeExp()!=null){
					context.put(step.getEndTimeExp(), new Date());
				}
				
			}
			
//			System.out.println(context.get("stepname1_starttime"));
//			System.out.println(context.get("stepname1_endtime"));
		}
	}

	public void init(String filePath){
		try {
			testJob = new XmlConfigBuilder().buildTestJob(filePath);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("load conf_file ["+filePath+"] failed", e);
		}
	}
}

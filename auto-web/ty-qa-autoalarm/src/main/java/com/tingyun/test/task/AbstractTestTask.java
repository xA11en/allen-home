package com.tingyun.test.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.test.TestJob;
import com.tingyun.test.TestStep;
import com.tingyun.test.TestTask;

public abstract class AbstractTestTask implements TestTask {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected TestJob job;
	protected TestStep step;
	protected Map<String,Object> params = new HashMap<String,Object>();
	
	protected String name;
	protected String startTimeExp;
	protected String endTimeExp;
	protected int sleepTime;
	
	public AbstractTestTask(){}
	
	public AbstractTestTask(TestStep step){
		this.step = step;
	}
	
	public TestJob getJob() {
		return job;
	}

	public void setJob(TestJob job) {
		this.job = job;
	}

	public TestStep getStep() {
		return step;
	}

	public void setStep(TestStep step) {
		this.step = step;
	}

	public void setParams(String key,Object value){
		this.params.put(key, value);
	}

	public String getStartTimeExp() {
		return startTimeExp;
	}

	public void setStartTimeExp(String startTimeExp) {
		this.startTimeExp = startTimeExp;
	}

	public String getEndTimeExp() {
		return endTimeExp;
	}

	public void setEndTimeExp(String endTimeExp) {
		this.endTimeExp = endTimeExp;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
